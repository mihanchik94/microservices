package ru.job4j.sservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.job4j.sservice.model.Student;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query("""
            SELECT s 
            FROM Student s 
            LEFT JOIN FETCH s.studentPicture 
            """)
    List<Student> findAll();

    @Query("""
            SELECT s 
            FROM Student s 
            LEFT JOIN FETCH s.studentPicture 
            WHERE s.gradeBookNumber = :gradeBookNumber
            """)
    Optional<Student> findByGradeBookNumber(@Param(value = "gradeBookNumber") String gradeBookNumber);
}
