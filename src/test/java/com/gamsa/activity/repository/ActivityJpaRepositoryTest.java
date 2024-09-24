package com.gamsa.activity.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.gamsa.activity.domain.Activity;
import com.gamsa.activity.entity.ActivityJpaEntity;
import com.gamsa.common.config.TestConfig;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort.Direction;

@DataJpaTest
@Import(TestConfig.class)
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
    private final ActivityJpaEntity jpaEntity2 = ActivityJpaEntity.builder()
        .actId(2L)
        .actTitle("어린이놀이안전관리 청소")
        .actLocation("아이사랑꿈터 서구 7호점")
        .description("봉사 내용2")
        .noticeStartDate(LocalDateTime.of(2024, 9, 11, 0, 0))
        .noticeEndDate(LocalDateTime.of(2024, 12, 8, 0, 0))
        .actStartDate(LocalDateTime.of(2024, 9, 11, 0, 0))
        .actEndDate(LocalDateTime.of(2024, 12, 8, 0, 0))
        .actStartTime(10)
        .actEndTime(20)
        .recruitTotalNum(2)
        .adultPossible(true)
        .teenPossible(false)
        .groupPossible(false)
        .actWeek(0111110)
        .actManager("홀란드")
        .actPhone("032-111-2222")
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
        Slice<Activity> result = activityJpaRepository.findSlice(PageRequest.of(0, 10));
        // then
        assertThat(result.getContent().size()).isEqualTo(1);
    }

    @Test
    void 활동_상세정보_조회() {
        // given
        activityJpaRepository.save(jpaEntity);
        // when
        Optional<ActivityJpaEntity> result = activityJpaRepository.findById(1L);
        // then
        assertThat(result.get().getActTitle()).isEqualTo(jpaEntity.getActTitle());
    }

    @Test
    void id로_정렬된_조회() {
        // given
        activityJpaRepository.save(jpaEntity);  // id = 1L
        activityJpaRepository.save(jpaEntity2); // id = 2L
        Pageable pageable = PageRequest.of(0, 2, Direction.DESC, "actId");

        // when
        Slice<Activity> result = activityJpaRepository.findSlice(pageable);

        // then
        assertThat(result.getSize()).isEqualTo(2);
        assertThat(result.getContent().getFirst().getActId()).isEqualTo(2L);
        assertThat(result.getContent().get(1).getActId()).isEqualTo(1L);
    }
}