package com.gamsa.activity.dto;

import com.gamsa.activity.domain.Activity;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ActivityFindAllResponse {

    private Long actId;
    private String actLocation;
    private LocalDateTime noticeStartDate;
    private LocalDateTime noticeEndDate;
    private LocalDateTime actStartDate;
    private LocalDateTime actEndDate;
    private int recruitTotalNum;

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
