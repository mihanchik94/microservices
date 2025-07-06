package ru.job4j.rservice.dto.event;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import static ru.job4j.rservice.util.ValidationConstants.REGEXP_VALIDATE_GRADE_BOOK_NUMBER;

@XmlRootElement(name = "getStudentByGradeBookNumberRequest")
public class GetStudentByGradeBookNumberRequest {

    @NotBlank(message = "The grade book number must not be blank")
    @Pattern(regexp = REGEXP_VALIDATE_GRADE_BOOK_NUMBER,
            message = "The grade book number must be in the following format:"
                    + " yy/std, where yy is the last 2 digits of the year of admission, "
                    + "and std is the student's number at the faculty upon admission.")
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
