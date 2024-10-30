package com.gamsa.history.dto;

import com.gamsa.activity.domain.Activity;
import com.gamsa.avatar.domain.Avatar;
import com.gamsa.history.constant.ActivityStatus;
import com.gamsa.history.domain.History;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class HistorySaveRequest {
    private final long actId;

    public History toModel(Avatar avatar, Activity activity) {
        return History.builder()
                .activity(activity)
                .avatar(avatar)
                .activityStatus(ActivityStatus.APPLIED)
                .reviewed(false)
                .build();
    }
}
