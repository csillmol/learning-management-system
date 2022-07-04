package com.codecool.examproject.learningmanagementsystem.model;

import com.codecool.examproject.learningmanagementsystem.dto.CourseDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_course_subject"))
    @JsonBackReference
    private Subject subject;

    private String lecturerName;

    private String lecturerPosition;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference
    private Set<Student> students;

    public Course(CourseDto courseDto) {
        this.id = courseDto.getId();
        this.subject = courseDto.getSubject();
        this.lecturerName = courseDto.getLecturerName();
        this.lecturerPosition = courseDto.getLecturerPosition();
        this.students = courseDto.getStudents();
    }
}
