package ru.job4j.rservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.ReactiveSubscription;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import ru.job4j.rservice.dto.event.ResponseWrapper;
import ru.job4j.rservice.exception.ServiceException;
import ru.job4j.rservice.util.XmlSerializer;

import java.time.Duration;

@Service
@Slf4j
@RequiredArgsConstructor
public class RedisService {
    private final ReactiveRedisTemplate<String, String> redisTemplate;
    private final XmlSerializer xmlSerializer;


    public Mono<Long> saveAndNotify(String key, String xmlResponse) {
        return redisTemplate.opsForValue().set(key, xmlResponse, Duration.ofMinutes(10))
                .then(redisTemplate.convertAndSend("response-channel", key))
                .doOnSuccess(count ->
                        log.info("Notified {} subscribers to {}", count, key)
                );
    }

    public Mono<Void> activateSubscription(String correlationId) {
        return redisTemplate.listenToChannel("response-channel")
                .map(ReactiveSubscription.Message::getMessage)
                .filter(msg -> msg.equals(correlationId))
                .next()
                .timeout(Duration.ofMillis(30), Mono.empty())
                .then()
                .doOnSubscribe(sub ->
                        log.info("Subscription activated for {}", correlationId)
                );
    }

    public <T extends ResponseWrapper<R>, R> Mono<R> waitForResponse(String correlationId, Class<T> responseType) {
        return redisTemplate.opsForValue().get(correlationId)
                .switchIfEmpty(
                        activateSubscription(correlationId)
                                .then(Mono.defer(() -> redisTemplate.opsForValue().get(correlationId)
                                        .timeout(Duration.ofMillis(40))
                                ))
                )
                .flatMap(xml -> deserializeAndValidate(xml, responseType))
                .doFinally(s -> redisTemplate.delete(correlationId).subscribe());
    }

    private <T extends ResponseWrapper<R>, R> Mono<R> deserializeAndValidate(String xml, Class<T> responseType) {
        return Mono.fromCallable(() -> {
            T response = responseType.cast(xmlSerializer.deserialize(xml, responseType));
            if (response.isHasError()) {
                throw new ServiceException(response.getErrorMessage());
            }
            return response.getData();
        }).subscribeOn(Schedulers.boundedElastic());
    }
}
