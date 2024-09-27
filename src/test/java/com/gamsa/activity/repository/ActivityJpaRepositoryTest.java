package com.gamsa.activity.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.gamsa.activity.constant.Category;
import com.gamsa.activity.dto.ActivityFilterRequest;
import com.gamsa.activity.entity.ActivityJpaEntity;
import com.gamsa.common.config.TestConfig;
import java.time.LocalDateTime;
import java.util.List;
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
        .noticeEndDate(LocalDateTime.of(2024, 9, 20, 0, 0))
        .actStartDate(LocalDateTime.of(2024, 9, 10, 0, 0))
        .actEndDate(LocalDateTime.of(2024, 9, 20, 0, 0))
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
        .build();

    private final ActivityJpaEntity jpaEntity2 = ActivityJpaEntity.builder()
        .actId(2L)
        .actTitle("어린이놀이안전관리 청소")
        .actLocation("아이사랑꿈터 서구 7호점")
        .description("봉사 내용2")
        .noticeStartDate(LocalDateTime.of(2024, 11, 1, 0, 0))
        .noticeEndDate(LocalDateTime.of(2024, 12, 8, 0, 0))
        .actStartDate(LocalDateTime.of(2024, 11, 1, 0, 0))
        .actEndDate(LocalDateTime.of(2024, 12, 8, 0, 0))
        .actStartTime(10)
        .actEndTime(20)
        .recruitTotalNum(2)
        .adultPossible(true)
        .teenPossible(true)
        .groupPossible(false)
        .actWeek(0111110)
        .actManager("홀란드")
        .actPhone("032-111-2222")
        .url("https://...")
        .category(Category.EDUCATION_AND_MENTORING)
        .build();

    private final ActivityJpaEntity jpaEntity3 = ActivityJpaEntity.builder()
        .actId(3L)
        .actTitle("학교")
        .actLocation("도서관")
        .description("책 정리")
        .noticeStartDate(LocalDateTime.of(2025, 1, 1, 0, 0))
        .noticeEndDate(LocalDateTime.of(2025, 1, 8, 0, 0))
        .actStartDate(LocalDateTime.of(2025, 1, 1, 0, 0))
        .actEndDate(LocalDateTime.of(2025, 1, 8, 0, 0))
        .actStartTime(10)
        .actEndTime(20)
        .recruitTotalNum(5)
        .adultPossible(true)
        .teenPossible(true)
        .groupPossible(false)
        .actWeek(0111110)
        .actManager("사서쌤")
        .actPhone("032-111-2222")
        .url("https://...")
        .category(Category.ADMINISTRATIVE_AND_OFFICE_SUPPORT)
        .build();

    // 필터링
    private final ActivityFilterRequest noFilterReq = new ActivityFilterRequest(
        null, false, false);

    private final ActivityFilterRequest otherCategoryFilterReq = new ActivityFilterRequest(
        Category.OTHER_ACTIVITIES, false, false);

    private final ActivityFilterRequest teenPossibleFilterReq = new ActivityFilterRequest(
        null, true, false);

    private final ActivityFilterRequest beforeDeadlineFilterReq = new ActivityFilterRequest(
        null, false, true);

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
        Slice<ActivityJpaEntity> result = activityJpaRepository.findSlice(noFilterReq,
            PageRequest.of(0, 10));
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
        Slice<ActivityJpaEntity> result = activityJpaRepository.findSlice(noFilterReq, pageable);

        // then
        assertThat(result.getSize()).isEqualTo(2);
        assertThat(result.getContent().getFirst().getActId()).isEqualTo(2L);
        assertThat(result.getContent().get(1).getActId()).isEqualTo(1L);
    }

    @Test
    void 마감_날짜_오름차순_정렬_조회() {
        // given
        activityJpaRepository.save(jpaEntity);
        activityJpaRepository.save(jpaEntity2);
        activityJpaRepository.save(jpaEntity3);
        Pageable pageable = PageRequest
            .of(0, 3, Direction.ASC, "noticeEndDate");

        // when
        List<ActivityJpaEntity> content = activityJpaRepository.findSlice(noFilterReq, pageable)
            .getContent();

        // then
        assertThat(content.size()).isEqualTo(3);
        assertThat(content.get(0).getNoticeEndDate().isBefore(content.get(1).getNoticeEndDate()))
            .isTrue();
        assertThat(content.get(1).getNoticeEndDate().isBefore(content.get(2).getNoticeEndDate()))
            .isTrue();
    }

    @Test
    void 마감된_공고중_마감_날짜_가까운순_정렬_조회() {
        // given
        LocalDateTime date = LocalDateTime.of(2024, 10, 1, 0, 0);
        activityJpaRepository.save(jpaEntity);
        activityJpaRepository.save(jpaEntity2);
        activityJpaRepository.save(jpaEntity3);
        Pageable pageable = PageRequest
            .of(0, 3, Direction.ASC, "noticeEndDate");

        // when
        List<ActivityJpaEntity> content1 = activityJpaRepository
            .findSlice(noFilterReq, pageable).getContent();
        List<ActivityJpaEntity> content2 = activityJpaRepository
            .findSlice(beforeDeadlineFilterReq, pageable).getContent();

        // then
        assertThat(content1.size()).isEqualTo(3);   // 필터링 X
        assertThat(content2.size()).isEqualTo(2);   // 필터링 O
        assertThat(content2.getFirst().getNoticeEndDate()
            .isBefore(content2.getLast().getNoticeEndDate()))
            .isTrue();
    }

    @Test
    void 카테고리로_필터링_조회() {
        // given
        activityJpaRepository.save(jpaEntity);
        activityJpaRepository.save(jpaEntity2);
        Pageable pageable = PageRequest.of(0, 2, Direction.ASC, "actId");

        // when
        List<ActivityJpaEntity> content = activityJpaRepository.findSlice(otherCategoryFilterReq,
            pageable).getContent();

        // then
        assertThat(content.size()).isEqualTo(1);
        assertThat(content.getFirst().getCategory()).isEqualTo(Category.OTHER_ACTIVITIES);
    }

    @Test
    void 청소년_가능여부_필터링_조회() {
        // given
        activityJpaRepository.save(jpaEntity);
        activityJpaRepository.save(jpaEntity2);
        Pageable pageable = PageRequest.of(0, 2, Direction.ASC, "actId");

        // when
        List<ActivityJpaEntity> content = activityJpaRepository.findSlice(teenPossibleFilterReq,
            pageable).getContent();

        // then
        assertThat(content.size()).isEqualTo(1);
        assertThat(content.getFirst().isTeenPossible()).isTrue();
    }

    @Test
    void 마감되지_않은_활동만_필터링_조회() {
        // given
        LocalDateTime date = LocalDateTime.of(2024, 10, 1, 0, 0);
        activityJpaRepository.save(jpaEntity);
        activityJpaRepository.save(jpaEntity2);
        Pageable pageable = PageRequest.of(0, 2, Direction.ASC, "actId");

        // when
        List<ActivityJpaEntity> content = activityJpaRepository.findSlice(beforeDeadlineFilterReq,
            pageable).getContent();

        // then
        assertThat(content.size()).isEqualTo(1);
        assertThat(content.getFirst().getNoticeEndDate().isAfter(date)).isTrue();
    }
}