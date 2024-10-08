package com.gamsa.activity.entity;

import static org.assertj.core.api.Assertions.assertThat;

import com.gamsa.activity.constant.Category;
import com.gamsa.activity.domain.Activity;
import com.gamsa.activity.domain.Institute;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class ActivityJpaEntityTest {

    @Test
    void 도메인모델에서_JPA엔티티로_변환() {
        // given
        Institute institute = Institute.builder()
            .instituteId(1L)
            .name("도서관")
            .location("서울시")
            .latitude(new BigDecimal("123456789.12341234"))
            .longitude(new BigDecimal("987654321.43214321"))
            .phone("010xxxxxxxx")
            .build();

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
            .category(Category.OTHER_ACTIVITIES)
            .institute(institute)
            .build();

        // when
        ActivityJpaEntity jpaEntity = ActivityJpaEntity.from(activity);

        // then
        assertThat(jpaEntity.getActId()).isEqualTo(activity.getActId());
    }

    @Test
    void JPA엔티에서_도메인모델로_변환() {
        // given
        InstituteJpaEntity institute = InstituteJpaEntity.builder()
            .instituteId(1L)
            .name("도서관")
            .location("서울시")
            .latitude(new BigDecimal("123456789.12341234"))
            .longitude(new BigDecimal("987654321.43214321"))
            .phone("010xxxxxxxx")
            .build();

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
            .category(Category.OTHER_ACTIVITIES)
            .institute(institute)
            .build();

        // when
        Activity activity = jpaEntity.toModel();

        // then
        assertThat(activity.getActId()).isEqualTo(jpaEntity.getActId());
    }
}