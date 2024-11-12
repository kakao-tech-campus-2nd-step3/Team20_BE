package com.gamsa.activity.entity;

import com.gamsa.activity.constant.Category;
import com.gamsa.activity.domain.Activity;
import com.gamsa.activity.domain.District;
import com.gamsa.activity.domain.Institute;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class ActivityJpaEntityTest {

    @Test
    void 도메인모델에서_JPA엔티티로_변환() {
        // given
        District district = District.builder()
                .sidoCode(1234)
                .sidoGunguCode(8888)
                .sidoName("서울특별시")
                .gunguName("강남구")
                .sido(false)
                .build();

        Institute institute = Institute.builder()
                .instituteId(1L)
                .name("도서관")
                .location("서울시")
                .latitude(new BigDecimal("37.56100278"))
                .longitude(new BigDecimal("126.9996417"))
                .sidoGungu(district)
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
                .sidoGungu(district)
                .build();

        // when
        ActivityJpaEntity jpaEntity = ActivityJpaEntity.from(activity);

        // then
        assertThat(jpaEntity.getActId()).isEqualTo(activity.getActId());
    }

    @Test
    void JPA엔티에서_도메인모델로_변환() {
        // given
        DistrictJpaEntity districtJpaEntity = DistrictJpaEntity.builder()
                .sidoCode(1234)
                .sidoGunguCode(8888)
                .sidoName("서울특별시")
                .gunguName("강남구")
                .sido(false)
                .build();

        InstituteJpaEntity institute = InstituteJpaEntity.builder()
                .instituteId(1L)
                .name("도서관")
                .location("서울시")
                .latitude(new BigDecimal("37.56100278"))
                .longitude(new BigDecimal("126.9996417"))
                .sidoGungu(districtJpaEntity)
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
                .sidoGungu(districtJpaEntity)
                .build();

        // when
        Activity activity = jpaEntity.toModel();

        // then
        assertThat(activity.getActId()).isEqualTo(jpaEntity.getActId());
    }
}