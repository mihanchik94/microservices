package ru.job4j.sservice.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class KafkaProducerProperties {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.producer.acks}")
    private String acks;

    @Value("${spring.kafka.producer.delivery-timeout-ms}")
    private String deliveryTimeout;

    @Value("${spring.kafka.producer.request-timeout-ms}")
    private String requestTimeout;

    @Value("${spring.kafka.producer.linger-ms}")
    private String linger;

    @Value("${spring.kafka.producer.idempotence}")
    private String idempotence;

    @Value("${spring.kafka.producer.max-in-flight-requests-per-connections}")
    private String maxInFlightRequests;

    @Value("${spring.kafka.topics.get-student-by-grade-book-number-response-topic.name}")
    private String getStudentByGradeBookNumberResponseTopicName;

    @Value("${spring.kafka.topics.get-student-by-grade-book-number-response-topic.partitions}")
    private int getStudentByGradeBookNumberResponseTopicPartitions;

    @Value("${spring.kafka.topics.get-student-by-grade-book-number-response-topic.replicas}")
    private int getStudentByGradeBookNumberResponseTopicReplicas;

    @Value("${spring.kafka.topics.get-all-students-response-topic.name}")
    private String getAllStudentsResponseTopicName;

    @Value("${spring.kafka.topics.get-all-students-response-topic.partitions}")
    private int getAllStudentsResponseTopicPartitions;

    @Value("${spring.kafka.topics.get-all-students-response-topic.replicas}")
    private int getAllStudentsResponseTopicReplicas;
}