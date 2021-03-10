package com.example.finalproj.rest;

import com.example.finalproj.repository.AnswerRepository;
import com.example.finalproj.repository.VoteRepository;
import com.example.finalproj.repository.dto.Question;
import com.example.finalproj.repository.dto.Vote;
import com.example.finalproj.rest.dto.QuestionDto;
import com.example.finalproj.rest.dto.QuestionWebDto;
import com.example.finalproj.rest.dto.VoteDto;
import com.example.finalproj.service.QuestionService;
import com.example.finalproj.service.UserService;
import com.example.finalproj.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/votes")
public class VoteResource {
    private QuestionService questionService;
    private AnswerRepository answerRepository;
    private UserService userService;
    @Autowired
    public VoteResource(UserService userService,QuestionService questionService, AnswerRepository answerRepository) {
        this.questionService = questionService;
        this.answerRepository = answerRepository;
        this.userService = userService;
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

    @GetMapping(path = "test", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void testVote(@RequestBody VoteDto vote) {
        Vote vote1 = new Vote();
        vote1.setVoteId(vote.getVoteId());
        vote1.setDate(vote.getDate());
        vote1.setAnswer(answerRepository.getAnswerByAnswerId(vote.getAnswerId()));
        vote1.setUser(userService.getUser(vote.getUserId()));
        System.out.println(vote1);
    }
}
