package com.codecool.examproject.learningmanagementsystem.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_course_subject"))
    private Subject subject;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_course_student"))
    private Student student;
}
