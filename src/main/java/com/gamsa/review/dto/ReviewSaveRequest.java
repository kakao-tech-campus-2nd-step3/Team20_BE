package com.gamsa.review.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewSaveRequest {

    private final Long historyId;
    private final List<AnswerSaveRequest> answers;
}
