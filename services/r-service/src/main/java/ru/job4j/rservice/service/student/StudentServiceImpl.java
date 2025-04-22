package ru.job4j.rservice.service.student;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import ru.job4j.rservice.dto.StudentDto;
import ru.job4j.rservice.dto.event.*;
import ru.job4j.rservice.service.RedisService;
import ru.job4j.rservice.service.soap.SoapStudentService;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService {
    private final SoapStudentService soapStudentService;
    private final RedisService redisService;
    private final Scheduler blockingScheduler;


    @Override
    public Mono<List<StudentDto>> getAll() {
        return processOperation(
                soapStudentService::publishGetStudents,
                GetAllStudentsResponse.class
        );
    }

    @Override
    public Mono<StudentDto> getStudentByGradeBookNumber(String gradeBookNumber) {
        return processOperation(
                key -> soapStudentService.publishGetStudentByGradeBookNumber(key, gradeBookNumber),
                GetStudentByGradeBookNumberResponse.class
        );
    }


    private <T extends ResponseWrapper<R>, R> Mono<R> processOperation(Consumer<String> kafkaSender, Class<T> responseType) {
        return Mono.fromCallable(UUID.randomUUID()::toString)
                .flatMap(cid ->
                        redisService.activateSubscription(cid)
                                .then(Mono.fromRunnable(() -> {
                                    kafkaSender.accept(cid);
                                    log.info("Request was send [ID: {}]", cid);
                                }))
                                .then(redisService.waitForResponse(cid, responseType))
                )
                .subscribeOn(blockingScheduler);
    }
}