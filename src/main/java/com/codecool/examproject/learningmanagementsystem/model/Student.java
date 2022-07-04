package com.codecool.examproject.learningmanagementsystem.model;

import com.codecool.examproject.learningmanagementsystem.dto.StudentDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    private String id;

    private String name;

    private String birthPlace;

    private LocalDate dateOfBirth;

    private Double gpa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_course_student"))
    @JsonBackReference
    private Course course;

    public Student(StudentDto studentDto) {
        this.id = studentDto.getId();
        this.name = studentDto.getName();
        this.birthPlace = studentDto.getBirthPlace();
        this.dateOfBirth = studentDto.getDateOfBirth();
        this.gpa = studentDto.getGpa();
        this.course = studentDto.getCourse();
    }
}
