package com.codecool.examproject.learningmanagementsystem.controller;

import com.codecool.examproject.learningmanagementsystem.dto.CourseDto;
import com.codecool.examproject.learningmanagementsystem.model.Course;
import com.codecool.examproject.learningmanagementsystem.service.CourseService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping
@OpenAPIDefinition(info = @Info(title = "Learning Management System"))
@Tag(name = "Operations of Courses")
public class CourseController {

    private final CourseService courseService;

    Logger logger = LoggerFactory.getLogger(CourseController.class);

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    @Operation(summary = "Lists all courses")
    public List<Course> findAll() {
        return courseService.findAll();
    }

    @GetMapping("courses/{id}")
    @Operation(summary = "Finds course by ID")
    public ResponseEntity<Course> findById(@PathVariable("id") Long id) {
        Course course;
        try {
            course = courseService.findById(id);
        } catch (NoSuchElementException e) {
            logger.error("Course with ID not found.");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(course);
    }

    @GetMapping("courses/subject/{name}")
    @Operation(summary = "Finds course by name of course")
    public ResponseEntity<List<Course>> findBySubjectName(@PathVariable("name") String name) {
        List<Course> course;
        try {
            course = courseService.findBySubjectName(name);
        } catch (NoSuchElementException e) {
            logger.error("Course not found.");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(course);
    }

    @GetMapping("courses/lecturer/{name}")
    @Operation(summary = "Finds course by name of lecturer")
    public ResponseEntity<List<Course>> findByLecturerName(@PathVariable("name") String lecturerName) {
        List<Course> course;
        try {
            course = courseService.findByLecturerName(lecturerName);
        } catch (NoSuchElementException e) {
            logger.error("Course not found.");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(course);
    }

    @PostMapping("courses")
    @Operation(summary = "Creates a course entry")
    public ResponseEntity<Course> save(@Valid @RequestBody CourseDto courseDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("Invalid input");
            bindingResult.getAllErrors().forEach(e -> logger.error(e.getDefaultMessage()));
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(courseService.save(courseDto));
    }

    @PutMapping("courses/{id}")
    @Operation(summary = "Updates an existing course by ID")
    public ResponseEntity<Course> update(@PathVariable("id") Long id, @Valid @RequestBody CourseDto courseDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("Invalid input");
            bindingResult.getAllErrors().forEach(e -> logger.error(e.getDefaultMessage()));
            return ResponseEntity.badRequest().build();
        }
        courseDTO.setId(id);
        return ResponseEntity.ok(courseService.updateCourse(courseDTO));
    }

    @DeleteMapping("courses/{id}")
    @Operation(summary = "Deletes an existing course by ID")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        try {
            courseService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            logger.error("Course with ID not found.");
            return ResponseEntity.badRequest().build();
        }
        logger.info("Successful delete");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
