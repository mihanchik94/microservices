package ru.job4j.rservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.job4j.rservice.dto.StudentDto;
import ru.job4j.rservice.service.student.StudentService;

import java.util.List;

@Tag(name = "Student Controller", description = "API for working with 'student' information")
@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @Operation(summary = "Get all students")
    @GetMapping("/all")
    public  Mono<ResponseEntity<List<StudentDto>>> getAll() {
        return studentService.getAll()
                .map(ResponseEntity::ok)
                .onErrorResume(e -> Mono.just(
                        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
                ));
    }

    @Operation(summary = "Get student by grade book number")
    @GetMapping()
    public Mono<ResponseEntity<StudentDto>> getStudentByGradeBookNumber(@RequestParam(name = "gradeBookNumber") String gradeBookNumber) {
        return studentService.getStudentByGradeBookNumber(gradeBookNumber)
                .map(ResponseEntity::ok)
                .onErrorResume(e -> Mono.just(
                        ResponseEntity.status(HttpStatus.NOT_FOUND).build()
                ));
    }
}
