package com.gamsa.activity.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Activity {

    private Long actId;
    private String actLocation;
    private String description;
    private LocalDateTime noticeStartDate;
    private LocalDateTime noticeEndDate;
    private LocalDateTime actStartDate;
    private LocalDateTime actEndDate;
    private int actStartTime;
    private int actEndTime;
    private int recruitTotalNum;
    private boolean adultPossible;
    private boolean teenPossible;
    private boolean groupPossible;
    private int actWeek;
    private String actManager;
    private String actPhone;
    private String url;

    @Builder
    public Activity(Long actId, String actLocation, String description,
        LocalDateTime noticeStartDate, LocalDateTime noticeEndDate,
        LocalDateTime actStartDate, LocalDateTime actEndDate,
        int actStartTime, int actEndTime, int recruitTotalNum, boolean adultPossible,
        boolean teenPossible, boolean groupPossible,
        int actWeek, String actManager, String actPhone, String url) {
        this.actId = actId;
        this.actLocation = actLocation;
        this.description = description;
        this.noticeStartDate = noticeStartDate;
        this.noticeEndDate = noticeEndDate;
        this.actStartDate = actStartDate;
        this.actEndDate = actEndDate;
        this.actStartTime = actStartTime;
        this.actEndTime = actEndTime;
        this.recruitTotalNum = recruitTotalNum;
        this.adultPossible = adultPossible;
        this.teenPossible = teenPossible;
        this.groupPossible = groupPossible;
        this.actWeek = actWeek;
        this.actManager = actManager;
        this.actPhone = actPhone;
        this.url = url;
    }
}
