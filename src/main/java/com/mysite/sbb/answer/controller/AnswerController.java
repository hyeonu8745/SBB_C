package com.mysite.sbb.answer.controller;

import com.mysite.sbb.answer.dto.AnswerDTO;
import com.mysite.sbb.answer.entity.Answer;
import com.mysite.sbb.answer.service.AnswerService;
import com.mysite.sbb.question.entity.Question;
import com.mysite.sbb.question.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequiredArgsConstructor
public class AnswerController {

    private final QuestionService questionService;
    private final AnswerService answerService;

    @PostMapping("/answer/create/{id}")
    public String createAnswer(@PathVariable("id") Long id, @Valid AnswerDTO answerDTO, BindingResult bindingResult, Model model) {
        log.info("===== id : {}, answerDTO : {}",id, answerDTO);
        Question question = questionService.getQuestion(id);

        if(bindingResult.hasErrors()) {
            model.addAttribute("question",question);
            return "question/detail";
        }

        answerService.createAnswer(question, answerDTO);
        return "redirect:/question/"+id;
    }
}
