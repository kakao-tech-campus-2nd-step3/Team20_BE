package com.gamsa.activity.entity;

import com.gamsa.activity.domain.Activity;
import com.gamsa.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "Activity")
public class ActivityJpaEntity extends BaseEntity {

    @Id
    @Column(name = "id")
    private Long actId;

    @Column(name = "act_title", length = 255)
    private String actTitle;

    @Column(name = "act_location", length = 255)
    private String actLocation;

    @Column(name = "description", length = 1024)
    private String description;

    @Column(name = "notice_start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime noticeStartDate;

    @Column(name = "notice_end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime noticeEndDate;

    @Column(name = "act_start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime actStartDate;

    @Column(name = "act_end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime actEndDate;

    @Column(name = "act_start_time")
    private int actStartTime;

    @Column(name = "act_end_time")
    private int actEndTime;

    @Column(name = "recruit_total_num")
    private int recruitTotalNum;

    @Column(name = "adult_possible")
    private boolean adultPossible;

    @Column(name = "teen_possible")
    private boolean teenPossible;

    @Column(name = "group_possible")
    private boolean groupPossible;

    @Column(name = "online_possible")
    private boolean onlinePossible;

    @Column(name = "act_week")
    private int actWeek;

    @Column(name = "act_manager", length = 255)
    private String actManager;

    @Column(name = "act_phone", length = 12)
    private String actPhone;

    @Column(name = "url", length = 255)
    private String url;

    public static ActivityJpaEntity from(Activity activity) {
        return ActivityJpaEntity.builder()
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
            .build();
    }

    public Activity toModel() {
        return Activity.builder()
            .actId(actId)
            .actTitle(actTitle)
            .actLocation(actLocation)
            .description(description)
            .noticeStartDate(noticeStartDate)
            .noticeEndDate(noticeEndDate)
            .actStartDate(actStartDate)
            .actEndDate(actEndDate)
            .actStartTime(actStartTime)
            .actEndTime(actEndTime)
            .recruitTotalNum(recruitTotalNum)
            .adultPossible(adultPossible)
            .teenPossible(teenPossible)
            .groupPossible(groupPossible)
            .actWeek(actWeek)
            .actManager(actManager)
            .actPhone(actPhone)
            .url(url)
            .build();
    }

}
