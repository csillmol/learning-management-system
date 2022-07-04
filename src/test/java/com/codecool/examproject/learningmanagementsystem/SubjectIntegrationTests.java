package com.codecool.examproject.learningmanagementsystem;

import com.codecool.examproject.learningmanagementsystem.dto.SubjectDto;
import com.codecool.examproject.learningmanagementsystem.model.Subject;
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

import static com.codecool.examproject.learningmanagementsystem.model.ExamType.EXAM;
import static com.codecool.examproject.learningmanagementsystem.model.ExamType.MIDTERM_MARK;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SubjectIntegrationTests {

    @Autowired
    TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String subjectUrl;

    SubjectDto subject1;
    SubjectDto subject2;

    private final List<SubjectDto> subjectDtos = List.of(
            subject1 = new SubjectDto(1L, "Introduction to Mathematics", MIDTERM_MARK, 4, null),
            subject2 = new SubjectDto(2L, "Introduction to Applied Linear Algebra", EXAM, 6, null));

    @BeforeEach
    void setUp() {
        String baseUrl = "http://localhost:" + port;
        subjectUrl = baseUrl + "/subjects";
        subject1 = new SubjectDto(1L, "Introduction to Mathematics", MIDTERM_MARK, 4, null);
        subject2 = new SubjectDto(2L, "Introduction to Applied Linear Algebra", EXAM, 6, null);
    }

    @Test
    @Order(1)
    void testGetSubjectById() {
        ResponseEntity<SubjectDto> responseEntity = restTemplate.getForEntity(subjectUrl + "/1", SubjectDto.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(subject1.getName(), responseEntity.getBody().getName());
        assertEquals(subject1.getExam(), responseEntity.getBody().getExam());
        assertEquals(subject1.getCredit(), responseEntity.getBody().getCredit());

        ResponseEntity<SubjectDto> badResponse = restTemplate.getForEntity(subjectUrl + "/7", SubjectDto.class);
        assertEquals(HttpStatus.NOT_FOUND, badResponse.getStatusCode());
    }


    @Test
    @Order(4)
    void testUpdateSubject() {
        restTemplate.postForObject("/subjects", subject2, SubjectDto.class);
        final Subject origSubject = restTemplate.postForObject(subjectUrl, subjectDtos.get(1), Subject.class);
        SubjectDto updateSubjectDto = new SubjectDto(
                origSubject.getId(),
                "Renamed Subject",
                origSubject.getExam(),
                origSubject.getCredit(),
                null
        );
        restTemplate.put(subjectUrl + "/" + updateSubjectDto.getId(), updateSubjectDto);

        final Subject updatedSubject = restTemplate.getForObject(subjectUrl + "/" + updateSubjectDto.getId(), Subject.class);
        assertEquals("Renamed Subject", updatedSubject.getName());
    }

    @Test
    @Order(5)
    void testDeleteSubject() {
        ResponseEntity<SubjectDto> responseEntity = restTemplate.getForEntity(subjectUrl + "/1", SubjectDto.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(subject1.getName(), responseEntity.getBody().getName());
        restTemplate.delete(subjectUrl + "/1");
        ResponseEntity<SubjectDto> deletedSubject = restTemplate.getForEntity(subjectUrl + "/1", SubjectDto.class);
        assertEquals(HttpStatus.NOT_FOUND, deletedSubject.getStatusCode());

        ResponseEntity<SubjectDto> badResponse = restTemplate.getForEntity(subjectUrl + "/7", SubjectDto.class);
        assertEquals(HttpStatus.NOT_FOUND, badResponse.getStatusCode());
    }
}
