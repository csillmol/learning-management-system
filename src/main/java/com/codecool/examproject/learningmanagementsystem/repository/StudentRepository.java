package com.codecool.examproject.learningmanagementsystem.repository;

import com.codecool.examproject.learningmanagementsystem.model.Student;
import com.codecool.examproject.learningmanagementsystem.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Student save(Student student);

    Optional<Student> findById(String id);

    Optional<Student> findByName(String name);

    @Query("select s from Student s where s.gpa >=:gpa order by s.gpa desc")
    List<Student> findByGpa(@Param("gpa") Double gpa);

    @Modifying
    @Query("delete from Student c where c.id=?1")
    void deleteById(String id);
}
