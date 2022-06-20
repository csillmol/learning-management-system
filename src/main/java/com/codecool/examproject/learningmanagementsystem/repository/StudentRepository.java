package com.codecool.examproject.learningmanagementsystem.repository;

import com.codecool.examproject.learningmanagementsystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    void deleteStudentById(Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Student SET name =:name, birthPlace =:birthPlace, dateOfBirth =:dateOfBirth, gpa =:gpa WHERE id =:id")
    void updateStudent(@Param("id") Long id, @Param("name") String name, @Param("birthPlace") String birthPlace, @Param("dateOfBirth") LocalDate dateOfBirth,
                     @Param("gpa") Double gpa);

}