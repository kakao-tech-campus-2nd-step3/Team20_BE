package com.gamsa.review.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AnswerSaveRequest {

    private final Integer questionId;
    private final int score;
}
