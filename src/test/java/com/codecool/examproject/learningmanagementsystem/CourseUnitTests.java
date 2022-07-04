package com.codecool.examproject.learningmanagementsystem;

import com.codecool.examproject.learningmanagementsystem.controller.CourseController;
import com.codecool.examproject.learningmanagementsystem.model.Course;
import com.codecool.examproject.learningmanagementsystem.service.CourseService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CourseUnitTests {

    @InjectMocks
    private CourseController courseController;

    @Mock
    private CourseService courseService;

    Course course1;
    Course course2;

    @BeforeEach
    void setUp() {
        course1 = new Course(1L, null, "Scott Bradley", "Assistant Professor", null);
        course2 = new Course(2L, null, "Matthew Smith", "Instructor", null);
    }

    @Test
    @Order(1)
    void testFindAll() {
        when(courseService.findAll()).thenReturn(List.of(course1, course2));
        List<Course> result = courseController.findAll();
        assertEquals(List.of(course1, course2), result);
        assertEquals(course1, result.get(0));
    }

    @Test
    @Order(2)
    void testFindById() {
        when(courseService.findById(2L)).thenReturn(course2);
        ResponseEntity<Course> responseEntity = courseController.findById(2L);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(course2, responseEntity.getBody());
    }

    @Test
    @Order(3)
    void testFindByWrongId() {
        when(courseService.findById(5L)).thenThrow(NoSuchElementException.class);
        ResponseEntity<Course> responseEntity = courseController.findById(5L);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}