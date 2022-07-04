package com.codecool.examproject.learningmanagementsystem.service;

import com.codecool.examproject.learningmanagementsystem.dto.StudentDto;
import com.codecool.examproject.learningmanagementsystem.model.Student;
import com.codecool.examproject.learningmanagementsystem.model.Subject;
import com.codecool.examproject.learningmanagementsystem.repository.StudentRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findById(String id) {
        return studentRepository.findById(id).get();
    }

    public Student findByName(String name) {
        return studentRepository.findByName(name).get();
    }

    public List<Student> findByGpa(Long gpa) {
        return studentRepository.findByGpa(gpa.doubleValue());
    }

    public Student save(@Valid StudentDto studentDto) {
        Student student = new Student(studentDto);
        return studentRepository.save(student);
    }

    @Transactional
    @Modifying
    public void deleteById(String id) {
        studentRepository.deleteById(id);
    }


    public Student updateStudent(StudentDto studentDto) {
        Student student = findById(studentDto.getId());
        student.setName(studentDto.getName());
        student.setBirthPlace(studentDto.getBirthPlace());
        student.setDateOfBirth(studentDto.getDateOfBirth());
        student.setGpa(studentDto.getGpa());
        student.setCourse(studentDto.getCourse());
        return studentRepository.save(student);
    }
}
