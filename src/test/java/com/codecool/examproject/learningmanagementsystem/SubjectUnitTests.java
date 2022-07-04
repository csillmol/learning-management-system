package com.codecool.examproject.learningmanagementsystem;

import com.codecool.examproject.learningmanagementsystem.controller.SubjectController;
import com.codecool.examproject.learningmanagementsystem.model.Subject;
import com.codecool.examproject.learningmanagementsystem.service.SubjectService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.NoSuchElementException;

import static com.codecool.examproject.learningmanagementsystem.model.ExamType.EXAM;
import static com.codecool.examproject.learningmanagementsystem.model.ExamType.MIDTERM_MARK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SubjectUnitTests {

    @InjectMocks
    private SubjectController subjectController;

    @Mock
    private SubjectService subjectService;

    Subject subject1;
    Subject subject2;

    @BeforeEach
    void setUp() {
        subject1 = new Subject(1L, "Introduction to Mathematics", MIDTERM_MARK, 4, null);
        subject2 = new Subject(2L, "Introduction to Applied Linear Algebra", EXAM, 6, null);
    }

    @Test
    @Order(1)
    void testFindAll() {
        when(subjectService.findAll()).thenReturn(List.of(subject1, subject2));
        List<Subject> result = subjectController.findAll();
        assertEquals(List.of(subject1, subject2), result);
        assertEquals(subject1, result.get(0));
    }

    @Test
    @Order(2)
    void testFindById() {
        when(subjectService.findById(2L)).thenReturn(subject2);
        ResponseEntity<Subject> responseEntity = subjectController.findById(2L);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(subject2, responseEntity.getBody());
    }

    @Test
    @Order(3)
    void testFindByWrongId() {
        when(subjectService.findById(5L)).thenThrow(NoSuchElementException.class);
        ResponseEntity<Subject> responseEntity = subjectController.findById(5L);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}
