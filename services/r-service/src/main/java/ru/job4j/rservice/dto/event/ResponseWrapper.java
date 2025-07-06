package ru.job4j.rservice.dto.event;

import jakarta.xml.bind.annotation.XmlSeeAlso;

@XmlSeeAlso({
        GetAllStudentsResponse.class,
        GetStudentByGradeBookNumberResponse.class
})
public interface ResponseWrapper<T> {
    T getData();
    boolean isHasError();
    String getErrorMessage();
}