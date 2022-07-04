package com.codecool.examproject.learningmanagementsystem;

import com.codecool.examproject.learningmanagementsystem.dto.CourseDto;
import com.codecool.examproject.learningmanagementsystem.model.Course;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CourseIntegrationTests {

    @Autowired
    TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String courseUrl;

    CourseDto course1;
    CourseDto course2;

    private final List<CourseDto> courseDtos = List.of(
            course1 = new CourseDto(1L, null, "Scott Bradley", "Assistant Professor", null),
            course2 = new CourseDto(2L, null, "Matthew Smith", "Instructor", null));

    @BeforeEach
    void setUp() {
        String baseUrl = "http://localhost:" + port;
        courseUrl = baseUrl + "/courses";
        course2 = new CourseDto(1L, null, "Scott Bradley", "Assistant Professor", null);
        course2 = new CourseDto(2L, null, "Matthew Smith", "Instructor", null);
    }

    @Test
    @Order(2)
    void testGetCourseById() {
        ResponseEntity<CourseDto> responseEntity = restTemplate.getForEntity(courseUrl + "/1", CourseDto.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(course1.getLecturerName(), responseEntity.getBody().getLecturerName());
        assertEquals(course1.getLecturerPosition(), responseEntity.getBody().getLecturerPosition());
        assertEquals(course1.getSubject(), responseEntity.getBody().getSubject());

        ResponseEntity<CourseDto> badResponse = restTemplate.getForEntity(courseUrl + "/7", CourseDto.class);
        assertEquals(HttpStatus.NOT_FOUND, badResponse.getStatusCode());
    }

    @Test
    @Order(4)
    void testUpdateCourse() {
        restTemplate.postForObject("/courses", course2, CourseDto.class);
        final Course origCourse = restTemplate.postForObject(courseUrl, courseDtos.get(1), Course.class);
        CourseDto updateCourseDto = new CourseDto(
                origCourse.getId(),
                origCourse.getSubject(),
                "Renamed Lecturer",
                origCourse.getLecturerPosition(),
                null
        );
        restTemplate.put(courseUrl + "/" + updateCourseDto.getId(), updateCourseDto);

        final Course updatedCourse = restTemplate.getForObject(courseUrl + "/" + updateCourseDto.getId(), Course.class);
        assertEquals("Renamed Lecturer", updatedCourse.getLecturerName());
    }

    @Test
    @Order(5)
    void testDeleteCourse() {
        ResponseEntity<CourseDto> responseEntity = restTemplate.getForEntity(courseUrl + "/1", CourseDto.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(course1.getLecturerName(), responseEntity.getBody().getLecturerName());
        restTemplate.delete(courseUrl + "/1");
        ResponseEntity<CourseDto> deletedCourse = restTemplate.getForEntity(courseUrl + "/1", CourseDto.class);
        assertEquals(HttpStatus.NOT_FOUND, deletedCourse.getStatusCode());

        ResponseEntity<CourseDto> badResponse = restTemplate.getForEntity(courseUrl + "/7", CourseDto.class);
        assertEquals(HttpStatus.NOT_FOUND, badResponse.getStatusCode());
    }
}
