package ru.job4j.rservice.dto.event;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import ru.job4j.rservice.dto.StudentDto;

@XmlRootElement(name = "getStudentByGradeBookNumberResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetStudentByGradeBookNumberResponse implements ResponseWrapper<StudentDto> {

    @XmlElement(name = "studentDto")
    private StudentDto studentDto;

    @XmlElement(name = "hasError")
    private boolean hasError;

    @XmlElement(name = "errorMessage")
    private String errorMessage;

    public GetStudentByGradeBookNumberResponse() {
    }

    public GetStudentByGradeBookNumberResponse(StudentDto studentDto, boolean hasError, String errorMessage) {
        this.studentDto = studentDto;
        this.hasError = hasError;
        this.errorMessage = errorMessage;
    }

    //@XmlElement(name = "studentDto")
    public StudentDto getData() {
        return studentDto;
    }

    public void setStudentDto(StudentDto studentDto) {
        this.studentDto = studentDto;
    }

    //@XmlElement(name = "hasError")
    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    //@XmlElement(name = "errorMessage")
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
