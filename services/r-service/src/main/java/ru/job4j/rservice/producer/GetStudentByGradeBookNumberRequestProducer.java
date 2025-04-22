package ru.job4j.rservice.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.job4j.rservice.dto.event.GetStudentByGradeBookNumberRequest;
import ru.job4j.rservice.util.XmlSerializer;

@Component
public class GetStudentByGradeBookNumberRequestProducer extends AbstractProducer<GetStudentByGradeBookNumberRequest> {
    public GetStudentByGradeBookNumberRequestProducer(KafkaTemplate<String, Object> kafkaTemplate,
                                                      XmlSerializer<GetStudentByGradeBookNumberRequest> serializer,
                                                      @Value("${spring.kafka.topics.get-student-by-grade-book-number-topic.name}")
                                                              String topicName) {
        super(kafkaTemplate, serializer, topicName);
    }
}
