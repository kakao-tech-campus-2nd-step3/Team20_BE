package com.gamsa.activity.dto;


import com.gamsa.activity.constant.Category;
import com.gamsa.activity.domain.Activity;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class ActivityDetailResponse {

    private final Long actId;
    private final String actTitle;
    private final String actLocation;
    private final String description;
    private final LocalDateTime noticeStartDate;
    private final LocalDateTime noticeEndDate;
    private final LocalDateTime actStartDate;
    private final LocalDateTime actEndDate;
    private final int actStartTime;
    private final int actEndTime;
    private final int recruitTotalNum;
    private final boolean adultPossible;
    private final boolean teenPossible;
    private final boolean groupPossible;
    private final int actWeek;
    private final String actManager;
    private final String actPhone;
    private final String url;
    private final Category category;

    public static ActivityDetailResponse from(Activity activity) {
        return ActivityDetailResponse.builder()
            .actId(activity.getActId())
            .actTitle(activity.getActTitle())
            .actLocation(activity.getActLocation())
            .description(activity.getDescription())
            .noticeStartDate(activity.getNoticeStartDate())
            .noticeEndDate(activity.getNoticeEndDate())
            .actStartDate(activity.getActStartDate())
            .actEndDate(activity.getActEndDate())
            .actStartTime(activity.getActStartTime())
            .actEndTime(activity.getActEndTime())
            .recruitTotalNum(activity.getRecruitTotalNum())
            .adultPossible(activity.isAdultPossible())
            .teenPossible(activity.isTeenPossible())
            .groupPossible(activity.isGroupPossible())
            .actWeek(activity.getActWeek())
            .actManager(activity.getActManager())
            .actPhone(activity.getActPhone())
            .url(activity.getUrl())
            .category(activity.getCategory())
            .build();
    }
}
