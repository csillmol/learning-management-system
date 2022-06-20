package com.codecool.examproject.learningmanagementsystem.controller;


import com.codecool.examproject.learningmanagementsystem.model.Course;
import com.codecool.examproject.learningmanagementsystem.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    public List<Course> findAll() {
        return courseService.findAll();
    }

    @GetMapping("courses/{id}")
    public Course findById(@PathVariable("id") Long id) {
        return courseService.findById(id);
    }

//    @PostMapping
//    public Course save(@RequestBody Course course) {
//        return courseService.save(course);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteCourseById(@PathVariable("id") Long id) {
//        courseService.deleteCourseById(id);
//    }
}

