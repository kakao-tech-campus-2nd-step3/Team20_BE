package com.gamsa.review.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Answer {

    private Question question;
    private int score;
}
