package com.codecool.examproject.learningmanagementsystem.controller;


import com.codecool.examproject.learningmanagementsystem.dto.SubjectDto;
import com.codecool.examproject.learningmanagementsystem.model.ExamType;
import com.codecool.examproject.learningmanagementsystem.model.Subject;
import com.codecool.examproject.learningmanagementsystem.service.SubjectService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping
@OpenAPIDefinition(info = @Info(title = "Learning Management System"))
@Tag(name = "Operations of Subjects")
public class SubjectController {

    private final SubjectService subjectService;

    Logger logger = LoggerFactory.getLogger(SubjectController.class);

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/subjects")
    @Operation(summary = "Lists all subjects")
    public List<Subject> findAll() {
        return subjectService.findAll();
    }

    @GetMapping("subjects/{id}")
    @Operation(summary = "Finds subject by ID")
    public ResponseEntity<Subject> findById(@PathVariable("id") Long id) {
        Subject subject;
        try {
            subject = subjectService.findById(id);
        } catch (NoSuchElementException e) {
            logger.error("Subject with ID not found.");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(subject);
    }

    @GetMapping("subjects/exam/{exam}")
    @Operation(summary = "Finds subject by exam type (exam / midterm_mark)")
    public ResponseEntity<List<Subject>> findByExam(@PathVariable("exam") String exam) {
        List<Subject> subject;
        try {
            subject = subjectService.findByExam(exam);
        } catch (NoSuchElementException e) {
            logger.error("Subject not found.");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(subject);
    }

    @GetMapping("subjects/credit/")
    @Operation(summary = "Lists subjects with credit equal or higher than query")
    public ResponseEntity<List<Subject>> findByCredit(@RequestParam Long credit) {
        List<Subject> subject;
        try {
            subject = subjectService.findByCredit(credit);
        } catch (NoSuchElementException e) {
            logger.error("Subject not found.");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(subject);
    }

    @PostMapping("subjects")
    @Operation(summary = "Creates a subject entry")
    public ResponseEntity<Subject> save(@Valid @RequestBody SubjectDto subjectDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("Invalid input");
            bindingResult.getAllErrors().forEach(e -> logger.error(e.getDefaultMessage()));
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(subjectService.save(subjectDto));
    }

    @PutMapping("subjects/{id}")
    @Operation(summary = "Updates an existing subject by ID")
    public ResponseEntity<Subject> update(@PathVariable("id") Long id, @Valid @RequestBody SubjectDto subjectDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("Invalid input");
            bindingResult.getAllErrors().forEach(e -> logger.error(e.getDefaultMessage()));
            return ResponseEntity.badRequest().build();
        }
        subjectDTO.setId(id);
        return ResponseEntity.ok(subjectService.updateSubject(subjectDTO));
    }

    @DeleteMapping("subjects/{id}")
    @Operation(summary = "Deletes an existing subject by ID")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        try {
            subjectService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            logger.error("Subject with ID not found.");
            return ResponseEntity.badRequest().build();
        }
        logger.info("Successful delete");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
