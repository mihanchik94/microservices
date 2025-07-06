package ru.job4j.sservice.consumer;

import jakarta.xml.bind.JAXBException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.core.serializer.support.SerializationFailedException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.job4j.sservice.dto.StudentDto;
import ru.job4j.sservice.dto.event.GetAllStudentsRequest;
import ru.job4j.sservice.dto.event.GetAllStudentsResponse;
import ru.job4j.sservice.producer.GetAllStudentsResponseProducer;
import ru.job4j.sservice.service.student.soap.SoapStudentServiceImpl;
import ru.job4j.sservice.util.XmlSerializer;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetAllStudentsRequestListener {
    private final SoapStudentServiceImpl soapStudentService;
    private final GetAllStudentsResponseProducer getAllStudentsResponseProducer;
    private final XmlSerializer<GetAllStudentsRequest> serializer;

    @KafkaListener(topics = "${spring.kafka.topics.get-all-students-request-topic.name}",
            containerFactory = "listenerContainerFactory", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(ConsumerRecord<String, String> record) {
        log.info("Received message: " + record.value());
        String key = record.key();
        log.info("Received response for correlationId: {}", key);
        try {
            serializer.deserialize(record.value(), GetAllStudentsRequest.class);
            List<StudentDto> studentDtoList =  soapStudentService.getStudents().getValue();
            GetAllStudentsResponse response = new GetAllStudentsResponse();
            response.setStudents(studentDtoList);
            getAllStudentsResponseProducer.publish(key, response);
        } catch (JAXBException e) {
            log.error("Error during deserializing: " + e.getMessage());
            GetAllStudentsResponse response = new GetAllStudentsResponse();
            response.setHasError(true);
            response.setErrorMessage("Error during deserializing: " + e.getMessage());
            getAllStudentsResponseProducer.publish(key, response);
            throw new SerializationFailedException("Error during deserializing: " + e.getMessage());
        }
    }
}