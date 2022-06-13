package com.codecool.examproject.learningmanagementsystem.service;

import com.codecool.examproject.learningmanagementsystem.model.Subject;
import com.codecool.examproject.learningmanagementsystem.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    public Subject findById(Long id) {
        return subjectRepository.findById(id).orElseThrow();
    }
}
