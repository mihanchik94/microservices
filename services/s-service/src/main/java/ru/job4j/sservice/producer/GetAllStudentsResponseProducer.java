package ru.job4j.sservice.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.job4j.sservice.dto.event.GetAllStudentsResponse;
import ru.job4j.sservice.util.XmlSerializer;

@Component
public class GetAllStudentsResponseProducer extends AbstractProducer<GetAllStudentsResponse> {
    public GetAllStudentsResponseProducer(KafkaTemplate<String, Object> kafkaTemplate, XmlSerializer<GetAllStudentsResponse> serializer,
                                          @Value("${spring.kafka.topics.get-all-students-response-topic.name}") String topicName) {
        super(kafkaTemplate, serializer, topicName);
    }
}
