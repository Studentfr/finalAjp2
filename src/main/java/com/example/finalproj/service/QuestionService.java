package com.example.finalproj.service;

import com.example.finalproj.repository.AnswerRepository;
import com.example.finalproj.repository.QuestionRepository;
import com.example.finalproj.repository.VoteRepository;
import com.example.finalproj.repository.dto.Answer;
import com.example.finalproj.repository.dto.Question;
import com.example.finalproj.rest.dto.AnswerWebDto;
import com.example.finalproj.rest.dto.QuestionWebDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    private QuestionRepository questionRepository;
    private AnswerRepository answerRepository;
    private VoteRepository voteRepository;
    @Autowired
    public QuestionService(QuestionRepository questionRepository, AnswerRepository answerRepository,
                           VoteRepository voteRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.voteRepository = voteRepository;
    }
    //TODO получение вопросов с вариантами ответов и ещё count для каждого варика как много ответили
    /*public List<QuestionWebDto> getQuestionWebDtoList() {
        List<QuestionWebDto> result = new ArrayList<>();
        List<Question> questionsList = questionRepository.findAll();
        for (Question q:
            questionsList) {
            QuestionWebDto dto = new QuestionWebDto();
            dto.setQuestion(q);
            List<Answer> answers = answerRepository.getAnswersByQuestion(q);
            for (Answer a:
                 answers) {
                long count = voteRepository.countVotesByAnswer(a);

            }
        }
    }*/
}
