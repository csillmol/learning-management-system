package com.codecool.examproject.learningmanagementsystem.repository;

import com.codecool.examproject.learningmanagementsystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
