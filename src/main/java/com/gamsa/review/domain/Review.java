package com.gamsa.review.domain;

import com.gamsa.activity.domain.Activity;
import com.gamsa.activity.domain.Institute;
import com.gamsa.avatar.domain.Avatar;
import com.gamsa.history.domain.History;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class Review {

    private Long reviewId;
    private List<Answer> answers;
    private Avatar avatar;
    private Activity activity;
    private Institute institute;
    private History history;
}
