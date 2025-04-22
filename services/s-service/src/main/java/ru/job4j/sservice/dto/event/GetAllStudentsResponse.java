package ru.job4j.sservice.dto.event;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import ru.job4j.sservice.dto.StudentDto;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "getAllStudentsResponse")
public class GetAllStudentsResponse {

    private List<StudentDto> students = new ArrayList<>();

    private boolean hasError;

    private String errorMessage;

    public GetAllStudentsResponse() {
    }

    public GetAllStudentsResponse(List<StudentDto> students, boolean hasError, String errorMessage) {
        this.students = students;
        this.hasError = hasError;
        this.errorMessage = errorMessage;
    }

    @XmlElementWrapper(name = "students")
    @XmlElement(name = "student")
    public List<StudentDto> getStudents() {
        return students;
    }

    public void setStudents(List<StudentDto> students) {
        this.students = students;
    }

    @XmlElement(name = "hasError")
    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    @XmlElement(name = "errorMessage")
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
