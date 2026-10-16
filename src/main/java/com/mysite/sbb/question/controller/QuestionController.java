package com.mysite.sbb.question.controller;

import com.mysite.sbb.question.entity.Question;
import com.mysite.sbb.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String detail(@PathVariable("id") Long id, Model model) {
        log.info("======== id : {}",id);
        Question question = questionService.getQuestion(id);
        model.addAttribute("question",question);
        return "question/detail";
    }

    @GetMapping("/create")
    public String createForm(){
        return "question/inputForm";
    }

    @PostMapping("/create")
    public String createQuestion(@RequestParam("subject") String subject,
                                 @RequestParam("content") String content){
        log.info("======== subject : {}, content : {}",subject,content);
        questionService.createQuestion(subject,content);

        return "redirect:/question/list";
    }
}
