package com.codecool.examproject.learningmanagementsystem.repository;

import com.codecool.examproject.learningmanagementsystem.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
