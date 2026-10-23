package com.mysite.sbb.question.controller;

import com.mysite.sbb.answer.dto.AnswerDTO;
import com.mysite.sbb.question.dto.QuestionDTO;
import com.mysite.sbb.question.entity.Question;
import com.mysite.sbb.question.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/list")
    public String list(Model model) {

        List<Question> list = questionService.getList();
        log.info("======list : {}",list);

        model.addAttribute("list",list);

        return "question/list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Long id, AnswerDTO answerDTO, Model model) {
        log.info("======== id : {}",id);
        Question question = questionService.getQuestion(id);
        model.addAttribute("question",question);
        return "question/detail";
    }

    @GetMapping("/create")
    public String createForm(QuestionDTO questionDTO) {
//        model.addAttribute("questionDTO",new QuestionDTO());
        return "question/inputForm";
    }

//    @PostMapping("/create")
//    public String createQuestion(@RequestParam("subject") String subject,
//                                 @RequestParam("content") String content){
//
//        log.info("======== subject : {}, content : {}",subject,content);
//        questionService.createQuestion(subject,content);
//
//        return "redirect:/question/list";
//    }

    @PostMapping("/create")
    public String createQuestion(@Valid QuestionDTO questionDTO, BindingResult bindingResult){

        log.info("======== questionDTO : {}, content : {}",questionDTO);
        if(bindingResult.hasErrors()){
            return "question/inputForm";
        }

        questionService.createQuestion(questionDTO);

        return "redirect:/question/list";
    }
}
