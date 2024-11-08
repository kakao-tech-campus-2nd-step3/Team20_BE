package com.gamsa.history.dto;

import com.gamsa.activity.dto.ActivityDetailResponse;
import com.gamsa.history.constant.ActivityStatus;
import com.gamsa.history.domain.History;
import com.gamsa.review.dto.QuestionResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Builder
@RequiredArgsConstructor
public class HistoryFindSliceResponse {

    private final long historyId;
    private final long avatarId;
    private final ActivityDetailResponse activity;
    private final ActivityStatus activityStatus;
    private final boolean reviewed;

    public static HistoryFindSliceResponse from(History history, Map<QuestionResponse, BigDecimal> scores) {
        return HistoryFindSliceResponse.builder()
                .historyId(history.getHistoryId())
                .avatarId(history.getAvatar().getAvatarId())
                .activityStatus(history.getActivityStatus())
                .reviewed(history.isReviewed())
                .activity(ActivityDetailResponse.from(history.getActivity(), scores))
                .build();
    }
}
