package com.codecool.examproject.learningmanagementsystem.repository;

import com.codecool.examproject.learningmanagementsystem.model.Course;
import com.codecool.examproject.learningmanagementsystem.model.ExamType;
import com.codecool.examproject.learningmanagementsystem.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("select distinct c from Course c left join fetch c.students s")
    List<Course> findAll();

    Optional<Course> findById(Long id);

    @Query("select c from Course c join Subject s on c.subject.id=s.id where s.name =:subjectName")
    List<Course> findBySubjectName(@Param("subjectName") String name);

    @Query("select c from Course c where c.lecturerName =:lecturerName")
    List<Course> findByLecturerName(@Param("lecturerName") String name);

    Course save(Course course);
}
