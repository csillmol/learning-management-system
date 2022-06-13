package com.codecool.examproject.learningmanagementsystem.repository;

import com.codecool.examproject.learningmanagementsystem.model.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, Long> {
}
