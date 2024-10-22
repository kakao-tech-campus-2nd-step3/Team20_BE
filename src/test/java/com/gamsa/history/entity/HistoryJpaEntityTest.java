package com.gamsa.history.entity;

import com.gamsa.activity.constant.Category;
import com.gamsa.activity.domain.Activity;
import com.gamsa.activity.entity.ActivityJpaEntity;
import com.gamsa.avatar.constant.AgeRange;
import com.gamsa.avatar.constant.Experienced;
import com.gamsa.avatar.domain.Avatar;
import com.gamsa.avatar.entity.AvatarJpaEntity;
import com.gamsa.history.constant.ActivityStatus;
import com.gamsa.history.domain.History;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class HistoryJpaEntityTest {
    // given
    Activity activity = Activity.builder()
            .actId(1L)
            .actTitle("어린이놀이안전관리 및 놀잇감 청결유지 및 정리")
            .actLocation("아이사랑꿈터 서구 5호점")
            .description("봉사 내용")
            .noticeStartDate(LocalDateTime.of(2024, 9, 10, 0, 0))
            .noticeEndDate(LocalDateTime.of(2024, 12, 7, 0, 0))
            .actStartDate(LocalDateTime.of(2024, 9, 10, 0, 0))
            .actEndDate(LocalDateTime.of(2024, 12, 7, 0, 0))
            .actStartTime(13)
            .actEndTime(18)
            .recruitTotalNum(1)
            .adultPossible(true)
            .teenPossible(false)
            .groupPossible(false)
            .actWeek(0111110)
            .actManager("윤순영")
            .actPhone("032-577-3026")
            .category(Category.OTHER_ACTIVITIES)
            .url("https://...")
            .build();

    Avatar avatar = Avatar.builder()
            .avatarId(1L)
            .avatarLevel(1L)
            .avatarExp(1L)
            .nickname("닉네임")
            .ageRange(AgeRange.ADULT)
            .experienced(Experienced.NOVICE)
            .build();

    @Test
    void 도메인에서_엔티티로() {
        // given
        History history = History.builder()
                .historyId(1L)
                .activity(activity)
                .avatar(avatar)
                .activityStatus(ActivityStatus.APPLIED)
                .reviewed(false)
                .build();

        //when
        HistoryJpaEntity historyJpaEntity = HistoryJpaEntity.from(history);

        //then
        assertThat(historyJpaEntity.getHistoryId()).isEqualTo(history.getHistoryId());

    }

    @Test
    void 엔티티에서_도메인으로() {
        //given
        HistoryJpaEntity historyJpaEntity = HistoryJpaEntity.builder()
                .historyId(1L)
                .activity(ActivityJpaEntity.from(activity))
                .avatar(AvatarJpaEntity.from(avatar))
                .activityStatus(ActivityStatus.APPLIED)
                .reviewed(false)
                .build();

        //when
        History history = historyJpaEntity.toModel();

        //then
        assertThat(history.getHistoryId()).isEqualTo(historyJpaEntity.getHistoryId());
    }
}
