package com.codecool.examproject.learningmanagementsystem.service;

import com.codecool.examproject.learningmanagementsystem.dto.CourseDto;
import com.codecool.examproject.learningmanagementsystem.model.Course;
import com.codecool.examproject.learningmanagementsystem.repository.CourseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
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
        return courseRepository.findById(id).get();
    }

    public List<Course> findBySubjectName(String name) {
        return courseRepository.findBySubjectName(name);
    }

    public List<Course> findByLecturerName(String name) {
        return courseRepository.findByLecturerName(name);
    }

    public Course save(@Valid CourseDto courseDto) {
        Course course = new Course(courseDto);
        return courseRepository.save(course);
    }

    @Transactional
    @Modifying
    public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }

    public Course updateCourse(CourseDto courseDto) {
        Course course = findById(courseDto.getId());
        course.setSubject(courseDto.getSubject());
        course.setLecturerName(courseDto.getLecturerName());
        course.setLecturerPosition(courseDto.getLecturerPosition());
        course.setStudents(courseDto.getStudents());
        return courseRepository.save(course);
    }
}
