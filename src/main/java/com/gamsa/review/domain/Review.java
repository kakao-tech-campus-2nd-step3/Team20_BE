package com.gamsa.review.domain;

import com.gamsa.activity.domain.Activity;
import com.gamsa.activity.domain.Institute;
import com.gamsa.history.domain.History;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Review {

    private Long reviewId;
    private List<Answer> answers;
    private Activity activity;
    private Institute institute;
    private History history;
}
