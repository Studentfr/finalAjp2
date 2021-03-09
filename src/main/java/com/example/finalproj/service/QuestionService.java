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
import org.springframework.transaction.annotation.Transactional;

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
    public void registerQuestion(Question question){
        questionRepository.save(question);
    }
    public void registerAnswers(Question question){
        for (Answer a : question.getAnswerOptions()) {
            a.setQuestion(question);
            answerRepository.save(a);
        }
    }
    @Transactional
    public void deleteQuestion(Long id){
        List<Answer> answers = answerRepository.getAnswersByQuestion(questionRepository.getQuestionByQuestionId(id));
        for (Answer a: answers
             ) {
            voteRepository.deleteVotesByAnswer(a);
        }
        answerRepository.deleteAnswersByQuestion(questionRepository.getQuestionByQuestionId(id));
        questionRepository.deleteQuestionByQuestionId(id);
    }

    public Question getQuestion(Long id){
        return questionRepository.getQuestionByQuestionId(id);
    }
    public void updateQuestion(Long id, Question question){
        Question updatedQ = questionRepository.findById(id).get();
        updatedQ.setQuestionText(question.getQuestionText());
        updatedQ.setAnswerOptions(question.getAnswerOptions());
        questionRepository.save(updatedQ);
    }
    public void updateAnswers(Long id, Question question){
        Question updatedQ = questionRepository.findById(id).get();
        for (Answer a : question.getAnswerOptions()) {
            a.setQuestion(updatedQ);
            answerRepository.save(a);
        }
    }
    public List<Answer> getAllAnswersByQuestionId(Long id){
        return answerRepository.getAnswersByQuestion(questionRepository.getQuestionByQuestionId(id));
    }

}
