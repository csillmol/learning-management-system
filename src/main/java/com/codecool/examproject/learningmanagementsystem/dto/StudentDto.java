package com.codecool.examproject.learningmanagementsystem.dto;

import com.codecool.examproject.learningmanagementsystem.model.Course;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

    @Id
    @Pattern(regexp = "[A-Z\\d]{6}")
    @Schema(description = "ID")
    private String id;

    @Schema(description = "Name of student")
    @NotBlank(message = "Student name is required")
    private String name;

    @Schema(description = "Birthplace of student")
    @NotBlank(message = "Birthplace is required")
    private String birthPlace;

    @Schema(description = "Date of birth of student", example = "YYYY-MM-DD")
    private LocalDate dateOfBirth;

    @Schema(description = "GPA of student", example = "3.6")
    @Range(max = 4)
    private Double gpa;

    @Schema(description = "Course of student")
    private Course course;
}
