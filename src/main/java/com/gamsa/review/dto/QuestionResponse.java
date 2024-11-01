package com.gamsa.review.dto;

import com.gamsa.review.domain.Question;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QuestionResponse {

    private final Integer questionId;
    private final String content;

    public static QuestionResponse from(Question question) {
        return QuestionResponse.builder()
            .questionId(question.getQuestionId())
            .content(question.getContent())
            .build();
    }
}
