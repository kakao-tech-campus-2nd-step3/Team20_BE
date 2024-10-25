package com.gamsa.history.domain;

import com.gamsa.activity.domain.Activity;
import com.gamsa.avatar.domain.Avatar;
import com.gamsa.history.constant.ActivityStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class History {
    private long historyId;
    private Avatar avatar;
    private Activity activity;
    private ActivityStatus activityStatus;
    private boolean reviewed;

    public void changeActivityStatusOnDate(LocalDateTime now) {
        if ((this.activityStatus == ActivityStatus.APPLIED)
                && (now.isAfter(activity.getNoticeEndDate()))) {
            this.activityStatus = ActivityStatus.WAITING;
        } else if ((this.activityStatus == ActivityStatus.WAITING)
                && (now.isAfter(activity.getActStartDate()))) {
            this.activityStatus = ActivityStatus.ACT;
        } else if (now.isAfter(activity.getActEndDate())) {
            this.activityStatus = ActivityStatus.FINISHED;
        }
    }

    public void changeReviewed(boolean reviewed) {
        this.reviewed = reviewed;
    }
}
