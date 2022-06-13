package com.codecool.examproject.learningmanagementsystem.repository;

import com.codecool.examproject.learningmanagementsystem.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
