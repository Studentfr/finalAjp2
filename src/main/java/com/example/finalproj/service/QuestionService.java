package com.example.finalproj.service;

import com.example.finalproj.repository.AnswerRepository;
import com.example.finalproj.repository.QuestionRepository;
import com.example.finalproj.repository.VoteRepository;
import com.example.finalproj.repository.dto.Account;
import com.example.finalproj.repository.dto.Answer;
import com.example.finalproj.repository.dto.Question;
import com.example.finalproj.repository.dto.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class QuestionService {
    private QuestionRepository questionRepository;
    private AnswerRepository answerRepository;
    private VoteRepository voteRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository, AnswerRepository answerRepository, VoteRepository voteRepository){
        this.questionRepository = questionRepository;
        this.voteRepository = voteRepository;
        this.answerRepository = answerRepository;
    }

    public List<Question> getAllQuestions(){
        return questionRepository.findAll();
    }
    public List<Answer> getAllAnswers(){
        return answerRepository.findAll();
    }
    public void setVote(long a, Account u){
        Vote vote = new Vote();
        Answer ans = answerRepository.getAnswerByAnswerId(a);
        vote.setAnswer(ans);
        vote.setDate(new Timestamp(new Date().getTime()));
        vote.setUser(u);
        voteRepository.save(vote);
    }

}
