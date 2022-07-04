package com.codecool.examproject.learningmanagementsystem.controller;

import com.codecool.examproject.learningmanagementsystem.dto.StudentDto;
import com.codecool.examproject.learningmanagementsystem.model.Student;
import com.codecool.examproject.learningmanagementsystem.service.StudentService;
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
@Tag(name = "Operations of Students")
public class StudentController {

    private final StudentService studentService;

    Logger logger = LoggerFactory.getLogger(StudentController.class);

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    @Operation(summary = "Lists all students")
    public List<Student> findAll() {
        return studentService.findAll();
    }

    @GetMapping("students/{id}")
    @Operation(summary = "Finds student by ID")
    public ResponseEntity<Student> findById(@Valid @PathVariable("id") String id) {
        Student student;
        try {
            student = studentService.findById(id);
        } catch (NoSuchElementException e) {
            logger.error("Student with ID not found.");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping("students/name/{name}")
    @Operation(summary = "Finds student by name")
    public ResponseEntity<Student> findByName(@Valid @PathVariable("name") String name) {
        Student student;
        try {
            student = studentService.findByName(name);
        } catch (NoSuchElementException e) {
            logger.error("Student with name not found.");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping("students/gpa/")
    @Operation(summary = "Lists students with GPA equal or higher than query")
    public ResponseEntity<List<Student>> findByGpa(@RequestParam Long gpa) {
        List<Student> student;
        try {
            student = studentService.findByGpa(gpa);
        } catch (NoSuchElementException e) {
            logger.error("Student with name not found.");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PostMapping("students")
    @Operation(summary = "Creates a student entry")
    public ResponseEntity<Student> save(@Valid @RequestBody StudentDto studentDto,
                                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("Invalid input");
            bindingResult.getAllErrors().forEach(e -> logger.error(e.getDefaultMessage()));
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(studentService.save(studentDto), HttpStatus.OK);
    }

    @PutMapping("students/{id}")
    @Operation(summary = "Updates an existing student by ID")
    public ResponseEntity<Student> update(@Valid @PathVariable("id") String id,
                                          @Valid @RequestBody StudentDto studentDTO,
                                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("Invalid input");
            bindingResult.getAllErrors().forEach(e -> logger.error(e.getDefaultMessage()));
            return ResponseEntity.badRequest().build();
        }
        studentDTO.setId(id);
        return new ResponseEntity<>(studentService.updateStudent(studentDTO), HttpStatus.OK);
    }

    @DeleteMapping("students/{id}")
    @Operation(summary = "Deletes an existing student by ID")
    public ResponseEntity<Void> deleteById(@Valid @PathVariable("id") String id) {
        try {
            studentService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            logger.error("Student with ID not found.");
            return ResponseEntity.badRequest().build();
        }
        logger.info("Successful delete");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
