package ru.job4j.sservice.dto.event;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "getStudentByGradeBookNumberRequest")
public class GetStudentByGradeBookNumberRequest {

    private String gradeBookNumber;

    public GetStudentByGradeBookNumberRequest() {
    }

    public GetStudentByGradeBookNumberRequest(String gradeBookNumber) {
        this.gradeBookNumber = gradeBookNumber;
    }

    @XmlElement(name = "gradeBookNumber")
    public String getGradeBookNumber() {
        return gradeBookNumber;
    }

    public void setGradeBookNumber(String gradeBookNumber) {
        this.gradeBookNumber = gradeBookNumber;
    }
}
