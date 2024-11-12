package com.gamsa.history.dto;

import com.gamsa.activity.dto.ActivityFindSliceResponse;
import com.gamsa.history.constant.ActivityStatus;
import com.gamsa.history.domain.History;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class HistoryFindSliceResponse {

    private final long historyId;
    private final long avatarId;
    private final ActivityFindSliceResponse activity;
    private final ActivityStatus activityStatus;
    private final boolean reviewed;

    public static HistoryFindSliceResponse from(History history) {
        return HistoryFindSliceResponse.builder()
                .historyId(history.getHistoryId())
                .avatarId(history.getAvatar().getAvatarId())
                .activityStatus(history.getActivityStatus())
                .reviewed(history.isReviewed())
                .activity(ActivityFindSliceResponse.from(history.getActivity()))
                .build();
    }
}
