package com.codecool.examproject.learningmanagementsystem.controller;


import com.codecool.examproject.learningmanagementsystem.model.Lecturer;
import com.codecool.examproject.learningmanagementsystem.service.LecturerService;
import org.springframework.web.bind.annotation.*;

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

//    @PostMapping
//    public Lecturer save(@RequestBody Lecturer lecturer) {
//        return lecturerService.save(lecturer);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteLecturerById(@PathVariable("id") Long id) {
//        lecturerService.deleteLecturerById(id);
//    }
}

