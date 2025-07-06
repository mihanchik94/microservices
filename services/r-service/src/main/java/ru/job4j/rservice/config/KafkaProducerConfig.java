package ru.job4j.rservice.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import ru.job4j.rservice.properties.KafkaProducerProperties;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KafkaProducerConfig {

    private final KafkaProducerProperties kafkaProducerProperties;

    @Bean
    public ProducerFactory<String, Object> producerFactory() {
        HashMap<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProducerProperties.getBootstrapServers());
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.ACKS_CONFIG, kafkaProducerProperties.getAcks());
        config.put(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG, kafkaProducerProperties.getDeliveryTimeout());
        config.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, kafkaProducerProperties.getRequestTimeout());
        config.put(ProducerConfig.LINGER_MS_CONFIG, kafkaProducerProperties.getLinger());
        config.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, kafkaProducerProperties.getIdempotence());
        config.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, kafkaProducerProperties.getMaxInFlightRequests());

        return new DefaultKafkaProducerFactory<>(config);
     }

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }


    @Bean
    public NewTopic getStudentByGradeBookNumberTopic() {
        return TopicBuilder.name(kafkaProducerProperties.getGetStudentByGradeBookNumberRequestTopicName())
                .partitions(kafkaProducerProperties.getGetStudentByGradeBookNumberRequestTopicPartitions())
                .replicas(kafkaProducerProperties.getGetStudentByGradeBookNumberRequestTopicReplicas())
                .configs(Map.of("min.insync.replicas", "2"))
                .build();
    }

    @Bean
    public NewTopic getAllStudentsTopic() {
        return TopicBuilder.name(kafkaProducerProperties.getGetAllStudentsTopicName())
                .partitions(kafkaProducerProperties.getGetAllStudentsPartitions())
                .replicas(kafkaProducerProperties.getGetAllStudentsReplicas())
                .configs(Map.of("min.insync.replicas", "2"))
                .build();
    }
}