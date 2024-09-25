package com.gamsa.activity.dto;

import com.gamsa.activity.domain.Activity;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class ActivityFindSliceResponse {

    private final Long actId;
    private final String actTitle;
    private final String actLocation;
    private final LocalDateTime noticeStartDate;
    private final LocalDateTime noticeEndDate;
    private final LocalDateTime actStartDate;
    private final LocalDateTime actEndDate;
    private final int actStartTime;
    private final int actEndTime;
    private final int recruitTotalNum;

    public static ActivityFindSliceResponse from(Activity activity) {
        return ActivityFindSliceResponse.builder()
            .actId(activity.getActId())
            .actTitle(activity.getActTitle())
            .actLocation(activity.getActLocation())
            .noticeStartDate(activity.getNoticeStartDate())
            .noticeEndDate(activity.getNoticeEndDate())
            .actStartDate(activity.getActStartDate())
            .actEndDate(activity.getActEndDate())
            .actStartTime(activity.getActStartTime())
            .actEndTime(activity.getActEndTime())
            .recruitTotalNum(activity.getRecruitTotalNum())
            .build();
    }
}
