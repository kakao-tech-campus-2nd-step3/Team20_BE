package com.gamsa.review.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Question {

    private Integer questionId;
    private String content;
}
