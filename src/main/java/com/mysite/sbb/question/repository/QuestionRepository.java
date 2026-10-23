package com.mysite.sbb.question.repository;

import com.mysite.sbb.question.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;



@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

//    Optional<Question> findBySubject(String subject);

    Question findBySubject(String subject);

    Optional<Question> findBySubjectAndContent(String subject, String content);

    List<Question> findBySubjectLike(String keyword);

    Page<Question> findAll(Pageable pageable);
}
