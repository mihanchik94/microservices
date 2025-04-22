package ru.job4j.sservice.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.job4j.sservice.dto.event.GetStudentByGradeBookNumberResponse;
import ru.job4j.sservice.util.XmlSerializer;

@Component
public class GetStudentByGradeBookNumberResponseProducer extends AbstractProducer<GetStudentByGradeBookNumberResponse> {
    public GetStudentByGradeBookNumberResponseProducer(KafkaTemplate<String, Object> kafkaTemplate,
                                                       XmlSerializer<GetStudentByGradeBookNumberResponse> serializer,
                                                       @Value("${spring.kafka.topics.get-student-by-grade-book-number-response-topic.name}") String topicName) {
        super(kafkaTemplate, serializer, topicName);
    }
}
