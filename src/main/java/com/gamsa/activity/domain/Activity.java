package com.gamsa.activity.domain;

import com.gamsa.activity.constant.Category;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Activity {

    private Long actId;
    private String actTitle;
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
    private Category category;
    private Institute institute;
}
