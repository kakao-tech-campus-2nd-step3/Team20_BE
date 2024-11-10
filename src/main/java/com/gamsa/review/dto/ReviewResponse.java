package com.gamsa.review.dto;

import com.gamsa.review.domain.Question;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class ReviewResponse {
    private QuestionResponse question;
    private BigDecimal score;

    public static ReviewResponse from(Question question, BigDecimal score) {
        return ReviewResponse.builder()
                .question(QuestionResponse.from(question))
                .score(score)
                .build();
    }
}
