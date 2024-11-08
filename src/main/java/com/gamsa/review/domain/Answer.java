package com.gamsa.review.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Answer {
    private Long answerId;
    private int score;
    private Question question;
}
