package ru.job4j.sservice.dto.event;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import ru.job4j.sservice.dto.StudentDto;

@XmlRootElement(name = "getStudentByGradeBookNumberResponse")
public class GetStudentByGradeBookNumberResponse {

    private StudentDto studentDto;

    private boolean hasError;

    private String errorMessage;

    public GetStudentByGradeBookNumberResponse() {
    }

    public GetStudentByGradeBookNumberResponse(StudentDto studentDto, boolean hasError, String errorMessage) {
        this.studentDto = studentDto;
        this.hasError = hasError;
        this.errorMessage = errorMessage;
    }

    @XmlElement(name = "studentDto")
    public StudentDto getStudentDto() {
        return studentDto;
    }

    public void setStudentDto(StudentDto studentDto) {
        this.studentDto = studentDto;
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
