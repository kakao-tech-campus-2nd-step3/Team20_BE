package com.gamsa.activity.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.gamsa.activity.constant.ActivityErrorCode;
import com.gamsa.activity.dto.ActivityDetailResponse;
import com.gamsa.activity.dto.ActivityFindSliceResponse;
import com.gamsa.activity.dto.ActivitySaveRequest;
import com.gamsa.activity.exception.ActivityException;
import com.gamsa.activity.stub.StubEmptyActivityRepository;
import com.gamsa.activity.stub.StubExistsActivityRepository;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;

class ActivityServiceTest {

    ActivitySaveRequest saveRequest = ActivitySaveRequest.builder()
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
    void 활동객체를_저장하고_성공한다() {
        // given
        ActivityService activityService = new ActivityService(new StubEmptyActivityRepository());

        // then
        Assertions.assertDoesNotThrow(() -> {
            // when
            activityService.save(saveRequest);
        });

    }

    @Test
    void 이미_존재하는_ID의_활동객체를_생성하고_실패한다() {
        // given
        ActivityService activityService = new ActivityService(new StubExistsActivityRepository());

        // then
        Assertions.assertThrows(ActivityException.class, () -> {
            // when
            activityService.save(saveRequest);
        }, ActivityErrorCode.ACTIVITY_ALREADY_EXISTS.getMsg());
    }

    @Test
    void 활동객체_리스트를_반환한다() {
        // given
        ActivityService activityService = new ActivityService(new StubEmptyActivityRepository());

        // when
        Slice<ActivityFindSliceResponse> result = activityService.findSlice(PageRequest.of(0, 10));

        // then
        assertThat(result.getContent().size()).isZero();
    }

    @Test
    void ID로_활동조회에_성공한다() {
        // given
        ActivityService activityService = new ActivityService(new StubExistsActivityRepository());

        // when
        ActivityDetailResponse result = activityService.findById(1L);

        // then
        assertThat(result.getActId()).isEqualTo(1L);
    }

    @Test
    void ID로_활동조회에_실패한다() {
        // given
        ActivityService activityService = new ActivityService(new StubEmptyActivityRepository());

        // then
        Assertions.assertThrows(ActivityException.class, () -> {
            // when
            activityService.findById(1L);
        }, ActivityErrorCode.ACTIVITY_NOT_EXISTS.getMsg());
    }
}