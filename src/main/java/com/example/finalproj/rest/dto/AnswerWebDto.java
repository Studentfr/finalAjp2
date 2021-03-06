package com.example.finalproj.rest.dto;

import lombok.Data;

@Data
public class AnswerWebDto {
    private long answerId;
    private String answerText;
    private long questionId;
    private long count;
}
