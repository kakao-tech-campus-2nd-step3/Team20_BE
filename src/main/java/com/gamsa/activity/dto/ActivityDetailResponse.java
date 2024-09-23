package com.gamsa.activity.dto;


import com.gamsa.activity.domain.Activity;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ActivityDetailResponse {

    private Long actId;
    private String actLocation;
    private String description;
    private LocalDateTime noticeStartDate;
    private LocalDateTime noticeEndDate;
    private LocalDateTime actStartDate;
    private LocalDateTime actEndDate;
    private int recruitTotalNum;
    private boolean adultPossible;
    private boolean teenPossible;
    private boolean groupPossible;
    private boolean onlinePossible;
    private int actWeek;
    private String actManager;
    private String actPhone;
    private String url;

    public static ActivityDetailResponse from(Activity activity) {
        return ActivityDetailResponse.builder()
            .actId(activity.getActId())
            .actLocation(activity.getActLocation())
            .description(activity.getDescription())
            .noticeStartDate(activity.getNoticeStartDate())
            .noticeEndDate(activity.getNoticeEndDate())
            .actStartDate(activity.getActStartDate())
            .actEndDate(activity.getActEndDate())
            .recruitTotalNum(activity.getRecruitTotalNum())
            .adultPossible(activity.isAdultPossible())
            .teenPossible(activity.isTeenPossible())
            .groupPossible(activity.isGroupPossible())
            .onlinePossible(activity.isOnlinePossible())
            .actWeek(activity.getActWeek())
            .actManager(activity.getActManager())
            .actPhone(activity.getActPhone())
            .url(activity.getUrl())
            .build();
    }
}
