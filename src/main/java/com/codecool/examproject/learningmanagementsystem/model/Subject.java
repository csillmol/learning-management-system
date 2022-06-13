package com.codecool.examproject.learningmanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Boolean exam;

    private int hoursPerWeek;

    private int credit;

    @ManyToMany(mappedBy = "subjects")
    @JsonIdentityReference(alwaysAsId = true)
    private Set<Lecturer> lecturer;

    @OneToMany(mappedBy = "subject")
    @JsonManagedReference
    private Set<Course> courses;
}