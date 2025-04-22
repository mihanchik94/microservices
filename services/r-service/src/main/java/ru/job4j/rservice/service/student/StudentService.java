package ru.job4j.rservice.service.student;

import reactor.core.publisher.Mono;
import ru.job4j.rservice.dto.StudentDto;

import java.util.List;

public interface StudentService {
    Mono<List<StudentDto>> getAll();
    Mono<StudentDto> getStudentByGradeBookNumber(String gradeBookNumber);
}