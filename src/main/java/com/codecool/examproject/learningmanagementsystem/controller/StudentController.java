package com.codecool.examproject.learningmanagementsystem.controller;


import com.codecool.examproject.learningmanagementsystem.model.Student;
import com.codecool.examproject.learningmanagementsystem.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<Student> findAll() {
        return studentService.findAll();
    }

    @GetMapping("students/{id}")
    public Student findById(@PathVariable("id") Long id) {
        return studentService.findById(id);
    }

    @PostMapping("students")
    public Student save(@RequestBody Student student) {
        return studentService.save(student);
    }

    @PutMapping("students/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody Student student) {
        studentService.updateStudent(id, student);
    }

    @DeleteMapping("students/{id}")
    public void deleteStudentById(@PathVariable("id") Long id) {
        studentService.deleteStudentById(id);
    }
}
