package com.gamsa.review.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Answer {
    private Long answerId;
    private Long reviewId;
    private double score;
    private Question question;
}
