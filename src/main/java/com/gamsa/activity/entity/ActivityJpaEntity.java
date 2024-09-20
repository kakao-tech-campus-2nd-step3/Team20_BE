package com.gamsa.activity.entity;

import com.gamsa.activity.domain.Activity;
import com.gamsa.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Entity
@Table(name = "Activity")
public class ActivityJpaEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long actId;

    @Column(name = "act_location")
    private String actLocation;

    @Column(name = "description")
    private String description;

    @Column(name = "notice_start_date")
    private LocalDateTime noticeStartDate;

    @Column(name = "notice_end_date")
    private LocalDateTime noticeEndDate;

    @Column(name = "act_start_date")
    private LocalDateTime actStartDate;

    @Column(name = "act_end_date")
    private LocalDateTime actEndDate;

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

    @Column(name = "act_manager")
    private String actManager;

    @Column(name = "act_phone")
    private String actPhone;

    @Column(name = "url")
    private String url;

    public static ActivityJpaEntity from(Activity activity) {
        return ActivityJpaEntity.builder()
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
