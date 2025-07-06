package ru.job4j.rservice.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class KafkaProducerConfigTest {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @MockBean
    public KafkaAdmin kafkaAdmin;

    @Test
    public void testProducerConfigWhenIdempotenceEnabledAssertsIdempotenceProperties() {
        ProducerFactory<String, Object> producerFactory = kafkaTemplate.getProducerFactory();
        Map<String, Object> configs = producerFactory.getConfigurationProperties();
        assertEquals("true", configs.get(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG));
        assertEquals("all", configs.get(ProducerConfig.ACKS_CONFIG));
        assertTrue(Integer.parseInt(configs.get(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION).toString()) <= 5);
        if (configs.containsKey(ProducerConfig.RETRIES_CONFIG)) {
            assertTrue(Integer.parseInt(configs.get(ProducerConfig.RETRIES_CONFIG).toString()) > 0);
        }
    }

}