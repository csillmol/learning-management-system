package com.codecool.examproject.learningmanagementsystem.model;

import com.codecool.examproject.learningmanagementsystem.dto.SubjectDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private ExamType exam;

    private int credit;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference
    private Set<Course> courses;

    public Subject(SubjectDto subjectDto) {
        this.id = subjectDto.getId();
        this.name = subjectDto.getName();
        this.exam = subjectDto.getExam();
        this.credit = subjectDto.getCredit();
        this.courses = subjectDto.getCourses();
    }
}