package com.codecool.examproject.learningmanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Lecturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String position;

    private int age;

    @ManyToMany
    @JoinTable(name = "subject_lecturer",
            joinColumns = @JoinColumn(name = "lecturer_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"),
            foreignKey = @ForeignKey(name = "fk_subject_lecturer"),
            inverseForeignKey = @ForeignKey(name = "fk_lecturer_subject"))
    @JsonIdentityReference(alwaysAsId = true)
    private List<Subject> subjects;
}
