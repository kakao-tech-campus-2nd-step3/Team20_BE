package com.gamsa.activity.dto;

import com.gamsa.activity.constant.Category;
import com.gamsa.activity.domain.Activity;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    private final BigDecimal latitude;
    private final BigDecimal longitude;
    private final int recruitTotalNum;
    private final Category category;

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
                .latitude(activity.getLatitude())
                .longitude(activity.getLongitude())
                .recruitTotalNum(activity.getRecruitTotalNum())
                .category(activity.getCategory())
                .build();
    }
}
