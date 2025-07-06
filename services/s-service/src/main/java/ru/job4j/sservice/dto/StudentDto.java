package ru.job4j.sservice.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Builder;

import java.io.Serializable;

@XmlRootElement
@Builder(setterPrefix = "with")
public class StudentDto implements Serializable {
    private Integer id;
    private String surname;
    private String name;
    private String patronymic;
    private String faculty;
    private String gradeBookNumber;
    private StudentPictureDto studentPictureDto;

    public StudentDto() {
    }

    public StudentDto(Integer id, String surname, String name, String patronymic, String faculty,
                      String gradeBookNumber, StudentPictureDto studentPictureDto) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.faculty = faculty;
        this.gradeBookNumber = gradeBookNumber;
        this.studentPictureDto = studentPictureDto;
    }

    @XmlElement
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @XmlElement
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @XmlElement
    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    @XmlElement
    public String getGradeBookNumber() {
        return gradeBookNumber;
    }

    public void setGradeBookNumber(String gradeBookNumber) {
        this.gradeBookNumber = gradeBookNumber;
    }

    @XmlElement
    public StudentPictureDto getStudentPictureDto() {
        return studentPictureDto;
    }

    public void setStudentPictureDto(StudentPictureDto studentPictureDto) {
        this.studentPictureDto = studentPictureDto;
    }
}