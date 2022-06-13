package com.codecool.examproject.learningmanagementsystem.service;

import com.codecool.examproject.learningmanagementsystem.model.Course;
import com.codecool.examproject.learningmanagementsystem.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Course findById(Long id) {
        return courseRepository.findById(id).orElseThrow();
    }
}
