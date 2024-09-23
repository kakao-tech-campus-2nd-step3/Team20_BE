package com.gamsa.activity.dto;

import com.gamsa.activity.domain.Activity;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ActivitySaveRequest {

    private final Long actId;
    private final String actLocation;
    private final String description;
    private final LocalDateTime noticeStartDate;
    private final LocalDateTime noticeEndDate;
    private final LocalDateTime actStartDate;
    private final LocalDateTime actEndDate;
    private final int recruitTotalNum;
    private final boolean adultPossible;
    private final boolean teenPossible;
    private final boolean groupPossible;
    private final boolean onlinePossible;
    private final int actWeek;
    private final String actManager;
    private final String actPhone;
    private final String url;

    public Activity toModel() {
        return Activity.builder()
            .actId(actId)
            .actLocation(actLocation)
            .description(description)
            .noticeStartDate(noticeStartDate)
            .noticeEndDate(noticeEndDate)
            .actStartDate(actStartDate)
            .actEndDate(actEndDate)
            .recruitTotalNum(recruitTotalNum)
            .adultPossible(adultPossible)
            .teenPossible(teenPossible)
            .groupPossible(groupPossible)
            .onlinePossible(onlinePossible)
            .actWeek(actWeek)
            .actManager(actManager)
            .actPhone(actPhone)
            .url(url)
            .build();
    }
}
