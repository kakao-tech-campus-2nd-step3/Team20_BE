package com.gamsa.activity.entity;

import static org.assertj.core.api.Assertions.assertThat;

import com.gamsa.activity.domain.Activity;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class ActivityJpaEntityTest {

    @Test
    void 도메인모델에서_JPA엔티티로_변환() {
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
            .url("https://...")
            .build();

        // when
        ActivityJpaEntity jpaEntity = ActivityJpaEntity.from(activity);

        // then
        assertThat(jpaEntity.getActId()).isEqualTo(activity.getActId());
    }

    @Test
    void JPA엔티에서_도메인모델로_변환() {
        // given
        ActivityJpaEntity jpaEntity = ActivityJpaEntity.builder()
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
            .url("https://...")
            .build();

        // when
        Activity activity = jpaEntity.toModel();

        // then
        assertThat(activity.getActId()).isEqualTo(jpaEntity.getActId());
    }
}