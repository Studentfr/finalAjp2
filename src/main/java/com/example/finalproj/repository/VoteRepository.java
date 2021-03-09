package com.example.finalproj.repository;

import com.example.finalproj.repository.dto.Answer;
import com.example.finalproj.repository.dto.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    Long countVotesByAnswer(Answer answer);
    void deleteVotesByAnswer(Answer a);
}
