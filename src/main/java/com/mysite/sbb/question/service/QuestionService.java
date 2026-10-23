package com.mysite.sbb.question.service;

import com.mysite.sbb.question.dto.QuestionDTO;
import com.mysite.sbb.question.entity.Question;
import com.mysite.sbb.question.repository.QuestionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List getList() {
        List<Question> questionList = questionRepository.findAll();
        return questionList;
    }

    public Question getQuestion(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 데이터 없음!"));
        return question;
    }

    public void createQuestion(QuestionDTO questionDTO) {
        Question question = Question.builder()
                .subject(questionDTO.getSubject())
                .content(questionDTO.getContent())
                .created(LocalDateTime.now())
                .build();

        questionRepository.save(question);
    }
}
