package com.example.finalproj.rest;

import com.example.finalproj.repository.dto.Question;
import com.example.finalproj.rest.dto.QuestionDto;
import com.example.finalproj.rest.dto.QuestionWebDto;
import com.example.finalproj.rest.dto.VoteDto;
import com.example.finalproj.service.QuestionService;
import com.example.finalproj.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/votes")
public class VoteResource {
    private QuestionService questionService;
    @Autowired
    public VoteResource(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public List<QuestionWebDto> getQuestions() {
        List<QuestionWebDto> dtoList = new ArrayList<>();
        List<Question> questions = questionService.getAllQuestions();
        Converter converter = new Converter();
        for (Question q:
             questions) {
            QuestionWebDto dto = new QuestionWebDto();
            dto.setQuestion(converter.questionToQuestionDto(q));
            dto.setAnswers(converter.answersToAnswerWebDto(q.getAnswerOptions()));
            dtoList.add(dto);
        }
        return dtoList;
    }
}
