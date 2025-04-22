package ru.job4j.rservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.job4j.rservice.dto.StudentDto;
import ru.job4j.rservice.service.student.StudentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;


    @GetMapping("/all")
    public  Mono<ResponseEntity<List<StudentDto>>> getAll() {
        return studentService.getAll()
                .map(ResponseEntity::ok)
                .onErrorResume(e -> Mono.just(
                        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
                ));
    }

    @GetMapping()
    public Mono<ResponseEntity<StudentDto>> getStudentByGradeBookNumber(@RequestParam(name = "gradeBookNumber") String gradeBookNumber) {
        return studentService.getStudentByGradeBookNumber(gradeBookNumber)
                .map(ResponseEntity::ok)
                .onErrorResume(e -> Mono.just(
                        ResponseEntity.status(HttpStatus.NOT_FOUND).build()
                ));
    }
}
