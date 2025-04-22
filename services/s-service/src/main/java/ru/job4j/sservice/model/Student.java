package ru.job4j.sservice.model;
import lombok.*;

import jakarta.persistence.*;


@Builder(setterPrefix = "with")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "faculty", nullable = false)
    private String faculty;

    @Column(name = "grade_book_number", nullable = false, unique = true)
    private String gradeBookNumber;

    @OneToOne(mappedBy = "student")
    private StudentPicture studentPicture;
}