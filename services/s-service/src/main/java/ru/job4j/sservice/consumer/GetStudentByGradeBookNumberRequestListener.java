package ru.job4j.sservice.consumer;

import jakarta.xml.bind.JAXBException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.core.serializer.support.SerializationFailedException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.job4j.sservice.dto.StudentDto;
import ru.job4j.sservice.dto.event.GetStudentByGradeBookNumberRequest;
import ru.job4j.sservice.dto.event.GetStudentByGradeBookNumberResponse;
import ru.job4j.sservice.producer.GetStudentByGradeBookNumberResponseProducer;
import ru.job4j.sservice.service.student.soap.SoapStudentServiceImpl;
import ru.job4j.sservice.util.XmlSerializer;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetStudentByGradeBookNumberRequestListener {

    private final SoapStudentServiceImpl soapStudentService;
    private final GetStudentByGradeBookNumberResponseProducer getStudentByGradeBookNumberResponseProducer;
    private final XmlSerializer<GetStudentByGradeBookNumberRequest> serializer;

    @KafkaListener(topics = "${spring.kafka.topics.get-student-by-grade-book-number-topic.name}", groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "listenerContainerFactory")
    public void listen(ConsumerRecord<String, String> record) {
        log.info("Received message: " + record.value());
        String key = record.key();
        log.info("Received response for correlationId: {}", key);
        try {
            GetStudentByGradeBookNumberRequest request = serializer.deserialize(record.value(), GetStudentByGradeBookNumberRequest.class);
            StudentDto studentDto = soapStudentService.getStudentByGradeBookNumber(request.getGradeBookNumber());
            GetStudentByGradeBookNumberResponse response = new GetStudentByGradeBookNumberResponse();
            response.setStudentDto(studentDto);
            getStudentByGradeBookNumberResponseProducer.publish(key, response);
        } catch (JAXBException e) {
            log.error("Error during deserializing: " + e.getMessage());
            GetStudentByGradeBookNumberResponse response = new GetStudentByGradeBookNumberResponse();
            response.setHasError(true);
            response.setErrorMessage("Error during deserializing: " + e.getMessage());
            getStudentByGradeBookNumberResponseProducer.publish(key, response);
            throw new SerializationFailedException("Error during deserializing: " + e.getMessage());
        }
    }
}
