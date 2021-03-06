package com.example.finalproj.rest.dto;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class VoteDto {
    private long voteId;
    private long userId;
    private long questionId;
    private long answerId;
    private Timestamp date;
}
