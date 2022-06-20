package com.codecool.examproject.learningmanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
//    @JoinColumn(foreignKey = @ForeignKey(name = "fk_course_subject"))
    @JsonBackReference
    private Subject subject;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(foreignKey = @ForeignKey(name = "fk_course_student"))
    @JsonBackReference
    private Student student;
}
