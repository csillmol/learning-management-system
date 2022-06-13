package com.codecool.examproject.learningmanagementsystem.controller;


import com.codecool.examproject.learningmanagementsystem.model.Lecturer;
import com.codecool.examproject.learningmanagementsystem.service.LecturerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class LecturerController {

    private final LecturerService lecturerService;

    public LecturerController(LecturerService lecturerService) {
        this.lecturerService = lecturerService;
    }

    @GetMapping("/lecturers")
    public List<Lecturer> findAll() {
        return lecturerService.findAll();
    }

    @GetMapping("lecturers/{id}")
    public Lecturer findById(@PathVariable("id") Long id) {
        return lecturerService.findById(id);
    }
}
