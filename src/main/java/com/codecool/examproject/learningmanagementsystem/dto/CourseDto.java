package com.codecool.examproject.learningmanagementsystem.dto;

import com.codecool.examproject.learningmanagementsystem.model.Student;
import com.codecool.examproject.learningmanagementsystem.model.Subject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID")
    private Long id;

    @Schema(description = "Subject")
    private Subject subject;

    @Schema(description = "Name of lecturer")
    @NotBlank(message = "Lecturer name is required")
    private String lecturerName;

    @Schema(description = "Position of lecturer")
    @NotBlank(message = "Lecturer position is required")
    private String lecturerPosition;

    @Schema(description = "List of students")
    private Set<Student> students;
}
