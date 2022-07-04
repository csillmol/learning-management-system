package com.codecool.examproject.learningmanagementsystem.repository;

import com.codecool.examproject.learningmanagementsystem.model.ExamType;
import com.codecool.examproject.learningmanagementsystem.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    Subject save(Subject subject);

    @Query("select distinct s from Subject s left join fetch s.courses c")
    List<Subject> findAll();

    Optional<Subject> findById(Long id);

    @Query("select s from Subject s where s.exam =:exam")
    List<Subject> findByExam(@Param("exam") ExamType exam);

    @Query("select s from Subject s where s.credit >=:credit")
    List<Subject> findByCredit(@Param("credit") Integer credit);
}
