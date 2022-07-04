package com.codecool.examproject.learningmanagementsystem;

import com.codecool.examproject.learningmanagementsystem.controller.StudentController;
import com.codecool.examproject.learningmanagementsystem.model.Student;
import com.codecool.examproject.learningmanagementsystem.service.StudentService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentUnitTests {

    @InjectMocks
    private StudentController studentController;

    @Mock
    private StudentService studentService;

    Student student1;
    Student student2;

    @BeforeEach
    void setUp() {
        student1 = new Student("P5BAHT", "Anna Broderick", "London", LocalDate.parse("2000-04-06"), 2.5, null);
        student2 = new Student("R54GGH", "Linda Moose", "Edinburgh", LocalDate.parse("2002-05-16"), 3.9, null);
    }

    @Test
    @Order(1)
    void testFindAll() {
        when(studentService.findAll()).thenReturn(List.of(student1, student2));
        List<Student> result = studentController.findAll();
        assertEquals(List.of(student1, student2), result);
        assertEquals(student1, result.get(0));
    }

    @Test
    @Order(2)
    void testFindById() {
        when(studentService.findById("P5BAHT")).thenReturn(student1);
        ResponseEntity<Student> responseEntity = studentController.findById("P5BAHT");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(student1, responseEntity.getBody());
    }

    @Test
    @Order(3)
    void testFindByWrongId() {
        when(studentService.findById("P5BA")).thenThrow(NoSuchElementException.class);
        ResponseEntity<Student> responseEntity = studentController.findById("P5BA");
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}
