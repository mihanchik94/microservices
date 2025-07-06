package ru.job4j.rservice.service.soap;

import jakarta.jws.WebService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.job4j.rservice.dto.event.*;
import ru.job4j.rservice.producer.GetAllStudentsRequestProducer;
import ru.job4j.rservice.producer.GetStudentByGradeBookNumberRequestProducer;


@WebService(
        serviceName = "StudentService",
        portName = "StudentPort",
        targetNamespace = "http://example.com/soap/",
        endpointInterface = "ru.job4j.rservice.service.soap.SoapStudentService")
@Slf4j
@Service
@RequiredArgsConstructor
public class SoapStudentServiceImpl implements SoapStudentService {

    private final GetStudentByGradeBookNumberRequestProducer getStudentByGradeBookNumberRequestProducer;
    private final GetAllStudentsRequestProducer getAllStudentsRequestProducer;


    @Override
    public void publishGetStudents(String key) {
        getAllStudentsRequestProducer.publish(key, new GetAllStudentsRequest());
        log.info("Published to Kafka [ID: {}]", key);
    }


    @Override
    public void publishGetStudentByGradeBookNumber(String key, String gradeBookNumber) {
        getStudentByGradeBookNumberRequestProducer.publish(key, new GetStudentByGradeBookNumberRequest(gradeBookNumber));
        log.info("Published to Kafka [ID: {}]", key);
    }
}
