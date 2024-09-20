package com.gamsa.activity.dto;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
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
}
