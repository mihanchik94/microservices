package ru.job4j.rservice.properties;

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

    @Value("${spring.kafka.topics.get-student-by-grade-book-number-topic.name}")
    private String getStudentByGradeBookNumberRequestTopicName;

    @Value("${spring.kafka.topics.get-student-by-grade-book-number-topic.partitions}")
    private int getStudentByGradeBookNumberRequestTopicPartitions;

    @Value("${spring.kafka.topics.get-student-by-grade-book-number-topic.replicas}")
    private int getStudentByGradeBookNumberRequestTopicReplicas;

    @Value("${spring.kafka.topics.get-all-students-request-topic.name}")
    private String getAllStudentsTopicName;

    @Value("${spring.kafka.topics.get-all-students-request-topic.partitions}")
    private int getAllStudentsPartitions;

    @Value("${spring.kafka.topics.get-all-students-request-topic.replicas}")
    private int getAllStudentsReplicas;
}