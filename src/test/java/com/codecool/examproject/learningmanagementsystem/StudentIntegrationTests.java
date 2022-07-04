package com.codecool.examproject.learningmanagementsystem;

import com.codecool.examproject.learningmanagementsystem.dto.StudentDto;
import com.codecool.examproject.learningmanagementsystem.model.Student;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
class StudentIntegrationTests {

    @Autowired
    TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String studentUrl;

    StudentDto student1;
    StudentDto student2;

    private final List<StudentDto> studentDtos = List.of(
            new StudentDto("P5BAHT", "Anna Broderick", "London", LocalDate.parse("2000-04-06"), 2.5, null),
            new StudentDto("R54GGH", "Linda Moose", "Edinburgh", LocalDate.parse("2002-05-16"), 3.9, null),
            new StudentDto("LSG65H", "Katherine Burgess", "Vienna", LocalDate.parse("1997-10-16"), 3.4, null),
            new StudentDto("AODH5K", "Frederick Welsh", "Edinburgh", LocalDate.parse("1999-08-13"), 1.4, null),
            new StudentDto("SDN9VG", "Jeremy Hughes", "Edinburgh", LocalDate.parse("2003-02-10"), 2.6, null),
            new StudentDto("GROCER", "Jonah Keys", "Edinburgh", LocalDate.parse("2000-01-06"), 3.0, null),
            new StudentDto("GPFLOW", "Frank Franklin", "Edinburgh", LocalDate.parse("2000-07-30"), 3.6, null),
            new StudentDto("ERLIDE", "Florence Soule", "Edinburgh", LocalDate.parse("2001-04-18"), 2.7, null));

    @BeforeEach
    void setUp() {
        String baseUrl = "http://localhost:" + port;
        studentUrl = baseUrl + "/students";
        student1 = new StudentDto("AODH5K", "Frederick Welsh", "Edinburgh", LocalDate.parse("1999-08-13"), 1.4, null);
        student2 = new StudentDto("R54GGH", "Linda Moose", "Edinburgh", LocalDate.parse("2002-05-16"), 3.9, null);
    }

    @Test
    @Order(1)
    void testGetStudentById() {
        ResponseEntity<StudentDto> responseEntity = restTemplate.getForEntity(studentUrl + "/AODH5K", StudentDto.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(student1.getName(), responseEntity.getBody().getName());
        assertEquals(student1.getBirthPlace(), responseEntity.getBody().getBirthPlace());
        assertEquals(student1.getDateOfBirth(), responseEntity.getBody().getDateOfBirth());
        assertEquals(student1.getGpa(), responseEntity.getBody().getGpa());

        ResponseEntity<StudentDto> badResponse = restTemplate.getForEntity(studentUrl + "/5", StudentDto.class);
        assertEquals(HttpStatus.NOT_FOUND, badResponse.getStatusCode());
    }

    @Test
    @Order(2)
    void testGetStudentByName() {
        ResponseEntity<StudentDto> responseEntity = restTemplate.getForEntity(studentUrl + "/name/Frederick Welsh", StudentDto.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(student1.getName(), responseEntity.getBody().getName());
        assertEquals(student1.getBirthPlace(), responseEntity.getBody().getBirthPlace());
        assertEquals(student1.getDateOfBirth(), responseEntity.getBody().getDateOfBirth());
        assertEquals(student1.getGpa(), responseEntity.getBody().getGpa());

        ResponseEntity<StudentDto> badResponse = restTemplate.getForEntity(studentUrl + "/5", StudentDto.class);
        assertEquals(HttpStatus.NOT_FOUND, badResponse.getStatusCode());
    }

    @Test
    @Order(3)
    void testUpdateStudent() {
        restTemplate.postForObject("/students", student2, StudentDto.class);
        final Student originalStudent = restTemplate.postForObject(studentUrl, studentDtos.get(1), Student.class);
        StudentDto updateStudentDto = new StudentDto(
                originalStudent.getId(),
                "Renamed Student",
                originalStudent.getBirthPlace(),
                originalStudent.getDateOfBirth(),
                originalStudent.getGpa(),
                null
        );
        restTemplate.put(studentUrl + "/" + updateStudentDto.getId(), updateStudentDto);

        final Student updatedStudent = restTemplate.getForObject(studentUrl + "/" + updateStudentDto.getId(), Student.class);
        assertEquals("Renamed Student", updatedStudent.getName());
    }

    @Test
    @Order(4)
    void testDeleteStudent() {
        ResponseEntity<StudentDto> responseEntity = restTemplate.getForEntity(studentUrl + "/AODH5K", StudentDto.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(student1.getName(), responseEntity.getBody().getName());
        restTemplate.delete(studentUrl + "/AODH5K");
        ResponseEntity<StudentDto> deletedStudent = restTemplate.getForEntity(studentUrl + "/AODH5K", StudentDto.class);
        assertEquals(HttpStatus.NOT_FOUND, deletedStudent.getStatusCode());

        ResponseEntity<StudentDto> badResponse = restTemplate.getForEntity(studentUrl + "/5", StudentDto.class);
        assertEquals(HttpStatus.NOT_FOUND, badResponse.getStatusCode());
    }
}
