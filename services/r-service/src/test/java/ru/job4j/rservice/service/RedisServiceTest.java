package ru.job4j.rservice.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import ru.job4j.rservice.util.XmlSerializer;

import java.time.Duration;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RedisServiceTest {

    @Mock
    private ReactiveRedisTemplate<String, String> redisTemplate;

    @Mock
    private ReactiveValueOperations<String, String> valueOperations;

    @Mock
    private XmlSerializer xmlSerializer;

    @InjectMocks
    private RedisService redisService;

    @Test
    void whenSaveAndNotifyThenSaveValueAndPublishMessage() {
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
        when(valueOperations.set(anyString(), anyString(), any(Duration.class)))
                .thenReturn(Mono.just(true));
        when(redisTemplate.convertAndSend(anyString(), anyString()))
                .thenReturn(Mono.just(1L));
        Mono<Long> result = redisService.saveAndNotify("testKey", "<xml>data</xml>");
        StepVerifier.create(result)
                .expectNext(1L)
                .verifyComplete();
        verify(valueOperations).set("testKey", "<xml>data</xml>", Duration.ofMinutes(10));
        verify(redisTemplate).convertAndSend("response-channel", "testKey");
    }
}