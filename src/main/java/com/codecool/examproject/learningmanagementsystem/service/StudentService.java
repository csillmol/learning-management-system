package com.codecool.examproject.learningmanagementsystem.service;

import com.codecool.examproject.learningmanagementsystem.model.Student;
import com.codecool.examproject.learningmanagementsystem.repository.StudentRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Transactional
    @Modifying
    public void deleteStudentById(Long id) {
        studentRepository.deleteStudentById(id);
    }


    public void updateStudent(Long id, Student student) {
        studentRepository.updateStudent(id, student.getName(), student.getBirthPlace(), student.getDateOfBirth(), student.getGpa());
    }
}
