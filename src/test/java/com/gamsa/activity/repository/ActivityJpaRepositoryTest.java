package com.gamsa.activity.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.gamsa.activity.entity.ActivityJpaEntity;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class ActivityJpaRepositoryTest {

    @Autowired
    private ActivityJpaRepository activityJpaRepository;
    private final ActivityJpaEntity jpaEntity = ActivityJpaEntity.builder()
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


    @Test
    void 새_활동_저장() {
        // when
        activityJpaRepository.save(jpaEntity);

        // then
        assertThat(activityJpaRepository.findById(1L).get().getActTitle())
            .isEqualTo(jpaEntity.getActTitle());
    }

    @Test
    void 모든_활동_리스트_반환() {
        // given
        activityJpaRepository.save(jpaEntity);

        // when
        List<ActivityJpaEntity> result = activityJpaRepository.findAll();

        // then
        assertThat(result.size()).isEqualTo(1);
    }
}