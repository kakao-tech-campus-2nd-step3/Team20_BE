package com.gamsa.activity.dto;

import com.gamsa.activity.domain.Activity;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class ActivityFindAllResponse {

    private final Long actId;
    private final String actLocation;
    private final LocalDateTime noticeStartDate;
    private final LocalDateTime noticeEndDate;
    private final LocalDateTime actStartDate;
    private final LocalDateTime actEndDate;
    private final int recruitTotalNum;

    public static ActivityFindAllResponse from(Activity activity) {
        return ActivityFindAllResponse.builder()
            .actId(activity.getActId())
            .actLocation(activity.getActLocation())
            .noticeStartDate(activity.getNoticeStartDate())
            .noticeEndDate(activity.getNoticeEndDate())
            .actStartDate(activity.getActStartDate())
            .actEndDate(activity.getActEndDate())
            .recruitTotalNum(activity.getRecruitTotalNum())
            .build();
    }
}
