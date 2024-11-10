package com.gamsa.activity.entity;

import com.gamsa.activity.constant.Category;
import com.gamsa.activity.constant.CategoryConverter;
import com.gamsa.activity.domain.Activity;
import com.gamsa.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "Activity")
public class ActivityJpaEntity extends BaseEntity {

    @Id
    @Column(name = "act_id")
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

    @Column(name = "latitude")
    private BigDecimal latitude;

    @Column(name = "longitude")
    private BigDecimal longitude;

    @Column(name = "act_week")
    private int actWeek;

    @Column(name = "act_manager", length = 255)
    private String actManager;

    @Column(name = "act_phone", length = 25)
    private String actPhone;

    @Column(name = "url", length = 255)
    private String url;

    @Convert(converter = CategoryConverter.class)
    @Column(name = "category", length = 255)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "institute_id")
    private InstituteJpaEntity institute;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sido_gungu_code", referencedColumnName = "sido_gungu_code")
    private DistrictJpaEntity sidoGungu;

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
                .longitude(activity.getLongitude())
                .latitude(activity.getLatitude())
                .actWeek(activity.getActWeek())
                .actManager(activity.getActManager())
                .actPhone(activity.getActPhone())
                .url(activity.getUrl())
                .category(activity.getCategory())
                .institute(InstituteJpaEntity.from(activity.getInstitute()))
                .sidoGungu(DistrictJpaEntity.from(activity.getSidoGungu()))
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
                .longitude(longitude)
                .latitude(latitude)
                .actWeek(actWeek)
                .actManager(actManager)
                .actPhone(actPhone)
                .url(url)
                .category(category)
                .institute(institute.toModel())
                .sidoGungu(sidoGungu.toModel())
                .build();
    }

}
