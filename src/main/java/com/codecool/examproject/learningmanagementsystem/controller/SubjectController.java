package com.codecool.examproject.learningmanagementsystem.controller;


import com.codecool.examproject.learningmanagementsystem.model.Subject;
import com.codecool.examproject.learningmanagementsystem.service.SubjectService;
import org.springframework.web.bind.annotation.*;

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

//    @PostMapping
//    public Subject save(@RequestBody Subject subject) {
//        return subjectService.save(subject);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteSubjectById(@PathVariable("id") Long id) {
//        subjectService.deleteSubjectById(id);
//    }
}
