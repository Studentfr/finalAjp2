package com.example.finalproj.service;

import com.example.finalproj.repository.VoteRepository;
import com.example.finalproj.repository.dto.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteService {
    private VoteRepository voteRepository;
    @Autowired
    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public long getNumberOfVotes(Answer answer) {
        return voteRepository.countVotesByAnswer(answer);
    }
}
