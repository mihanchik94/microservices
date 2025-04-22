package ru.job4j.rservice.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.job4j.rservice.dto.event.GetAllStudentsRequest;
import ru.job4j.rservice.util.XmlSerializer;

@Component
public class GetAllStudentsRequestProducer extends AbstractProducer<GetAllStudentsRequest> {
    public GetAllStudentsRequestProducer(KafkaTemplate<String, Object> kafkaTemplate, XmlSerializer<GetAllStudentsRequest> serializer,
                                         @Value("${spring.kafka.topics.get-all-students-request-topic.name}") String topicName) {
        super(kafkaTemplate, serializer, topicName);
    }
}