package com.codecool.examproject.learningmanagementsystem.service;

import com.codecool.examproject.learningmanagementsystem.dto.SubjectDto;
import com.codecool.examproject.learningmanagementsystem.model.ExamType;
import com.codecool.examproject.learningmanagementsystem.model.Subject;
import com.codecool.examproject.learningmanagementsystem.repository.SubjectRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
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
        return subjectRepository.findById(id).get();
    }

   public List<Subject> findByExam(String exam) {
        return subjectRepository.findByExam(ExamType.valueOf(exam));
    }

    public List<Subject> findByCredit(Long credit) {
        return subjectRepository.findByCredit(credit.intValue());
    }

    public Subject save(@Valid SubjectDto subjectDto) {
        Subject subject = new Subject(subjectDto);
        return subjectRepository.save(subject);
    }

    @Transactional
    @Modifying
    public void deleteById(Long id) {
        subjectRepository.deleteById(id);
    }


    public Subject updateSubject(SubjectDto subjectDto) {
        Subject subject = findById(subjectDto.getId());
        subject.setName(subjectDto.getName());
        subject.setExam(subjectDto.getExam());
        subject.setCredit(subjectDto.getCredit());
        subject.setCourses(subjectDto.getCourses());
        return subjectRepository.save(subject);
    }
}
