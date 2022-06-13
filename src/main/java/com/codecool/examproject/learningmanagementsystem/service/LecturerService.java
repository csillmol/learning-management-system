package com.codecool.examproject.learningmanagementsystem.service;

import com.codecool.examproject.learningmanagementsystem.model.Lecturer;
import com.codecool.examproject.learningmanagementsystem.repository.LecturerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LecturerService {

    private final LecturerRepository lecturerRepository;

    public LecturerService(LecturerRepository lecturerRepository) {
        this.lecturerRepository = lecturerRepository;
    }

    public List<Lecturer> findAll() {
        return lecturerRepository.findAll();
    }

    public Lecturer findById(Long id) {
        return lecturerRepository.findById(id).orElseThrow();
    }
}
