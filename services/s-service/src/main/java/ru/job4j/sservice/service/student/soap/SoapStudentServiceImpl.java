package ru.job4j.sservice.service.student.soap;

import jakarta.jws.WebService;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.JAXBElement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.sservice.dto.StudentDto;
import ru.job4j.sservice.mapper.StudentMapper;
import ru.job4j.sservice.model.Student;
import ru.job4j.sservice.repository.StudentRepository;
import ru.job4j.sservice.service.student.StudentService;

import javax.xml.namespace.QName;

@WebService(
        serviceName = "StudentService",
        portName = "StudentPort",
        targetNamespace = "http://example.com/soap/",
        endpointInterface = "ru.job4j.sservice.service.student.StudentService")
@Service
@RequiredArgsConstructor
public class SoapStudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    @Transactional
    public JAXBElement<List<StudentDto>> getStudents() {
        List<StudentDto> result = studentMapper.toDtoList(studentRepository.findAll());
        return (JAXBElement<List<StudentDto>>) new JAXBElement(new QName("http://example.com/soap/", "getStudents"),
                ArrayList.class, result);
    }

    @Override
    @Transactional
    public StudentDto getStudentByGradeBookNumber(String gradeBookNumber) {
        return studentMapper.toDto(findStudentByGradeBookNumber(gradeBookNumber));
    }


    private Student findStudentByGradeBookNumber(String gradeBookNumber) {
        return studentRepository.findByGradeBookNumber(gradeBookNumber)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }
}
