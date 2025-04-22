package ru.job4j.rservice.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import ru.job4j.rservice.service.RedisService;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractResponseListener {
    protected final RedisService redisService;

    public void processMessage(ConsumerRecord<String, String> record) {
        String response = record.value();
        log.info("Received message: " + response);
        String key = record.key();
        log.info("Received response for correlationId: {}", key);
        redisService.saveAndNotify(key, response)
                .subscribe(
                        count -> log.debug("Processed with {} subscribers", count),
                        error -> log.error("Error processing: ", error)
                );
    }
}
