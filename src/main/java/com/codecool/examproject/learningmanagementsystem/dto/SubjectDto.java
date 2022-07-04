package com.codecool.examproject.learningmanagementsystem.dto;

import com.codecool.examproject.learningmanagementsystem.model.Course;
import com.codecool.examproject.learningmanagementsystem.model.ExamType;
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
public class SubjectDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID")
    private Long id;

    @Schema(description = "Subject")
    @NotBlank(message = "Subject name is required")
    private String name;

    @Schema(description = "Exam / Mid-term mark")
    private ExamType exam;

    @Schema(description = "Credits")
    private Integer credit;

    @Schema(description = "List of courses")
    private Set<Course> courses;
}
