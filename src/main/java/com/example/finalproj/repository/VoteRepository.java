package com.example.finalproj.repository;

import com.example.finalproj.repository.dto.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}
