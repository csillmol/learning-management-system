package com.codecool.examproject.learningmanagementsystem.service;

import com.codecool.examproject.learningmanagementsystem.model.Student;
import com.codecool.examproject.learningmanagementsystem.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findById(Long id) {
        return studentRepository.findById(id).orElseThrow();
    }
}
