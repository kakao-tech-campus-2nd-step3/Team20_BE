package com.gamsa.history.entity;


import com.gamsa.activity.entity.ActivityJpaEntity;
import com.gamsa.avatar.entity.AvatarJpaEntity;
import com.gamsa.common.entity.BaseEntity;
import com.gamsa.history.constant.ActivityStatus;
import com.gamsa.history.constant.ActivityStatusConverter;
import com.gamsa.history.domain.History;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "History")
public class HistoryJpaEntity extends BaseEntity {
    @Id
    @GeneratedValue()
    @Column(name = "history_id")
    private long historyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "avatar_id")
    private AvatarJpaEntity avatar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_id")
    private ActivityJpaEntity activity;

    @Convert(converter = ActivityStatusConverter.class)
    @Column(name = "activity_status")
    private ActivityStatus activityStatus;

    @Column(name = "reviewed")
    private boolean reviewed;

    public static HistoryJpaEntity from(History history) {
        return HistoryJpaEntity.builder()
                .historyId(history.getHistoryId())
                .avatar(AvatarJpaEntity.from(history.getAvatar()))
                .activity(ActivityJpaEntity.from(history.getActivity()))
                .activityStatus(history.getActivityStatus())
                .reviewed(history.isReviewed())
                .build();
    }

    public History toModel() {
        return History.builder()
                .historyId(historyId)
                .avatar(avatar.toModel())
                .activity(activity.toModel())
                .activityStatus(activityStatus)
                .reviewed(reviewed)
                .build();
    }
}
