package com.gamsa.review.domain;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Review {

    private List<Answer> answers;
}
