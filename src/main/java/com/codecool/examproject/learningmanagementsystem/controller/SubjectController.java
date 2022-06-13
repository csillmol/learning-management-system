package com.codecool.examproject.learningmanagementsystem.controller;


import com.codecool.examproject.learningmanagementsystem.model.Subject;
import com.codecool.examproject.learningmanagementsystem.service.SubjectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/subjects")
    public List<Subject> findAll() {
        return subjectService.findAll();
    }

    @GetMapping("subjects/{id}")
    public Subject findById(@PathVariable("id") Long id) {
        return subjectService.findById(id);
    }
}
