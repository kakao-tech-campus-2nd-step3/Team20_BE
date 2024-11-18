package com.gamsa.activity.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.gamsa.activity.constant.ActivityErrorCode;
import com.gamsa.activity.dto.ActivityDetailResponse;
import com.gamsa.activity.dto.ActivityFindSliceResponse;
import com.gamsa.activity.exception.ActivityException;
import com.gamsa.activity.stub.StubEmptyActivityRepository;
import com.gamsa.activity.stub.StubExistsActivityRepository;
import com.gamsa.history.stub.StubHistoryRepository;
import com.gamsa.review.service.QuestionService;
import com.gamsa.review.service.ReviewService;
import com.gamsa.review.stub.StubQuestionRepository;
import com.gamsa.review.stub.StubReviewRepository;
import com.gamsa.user.stub.StubExistsUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;

class ActivityServiceTest {


    @Test
    void 활동객체_리스트를_반환한다() {
        // given
        ActivityService activityService = new ActivityService(
                new StubEmptyActivityRepository(),
                new QuestionService(
                        new StubQuestionRepository()
                ),
                new ReviewService(
                        new StubExistsUserRepository(),
                        new StubQuestionRepository(),
                        new StubReviewRepository(),
                        new StubHistoryRepository()
                )
        );

        // when
        Slice<ActivityFindSliceResponse> result = activityService.findSlice(null, null,
                PageRequest.of(0, 10));

        // then
        assertThat(result.getContent().size()).isZero();
    }

    @Test
    void ID로_활동조회에_성공한다() {
        // given
        ActivityService activityService = new ActivityService(
                new StubExistsActivityRepository(),
                new QuestionService(
                        new StubQuestionRepository()
                ),
                new ReviewService(
                        new StubExistsUserRepository(),
                        new StubQuestionRepository(),
                        new StubReviewRepository(),
                        new StubHistoryRepository()
                )
        );

        // when
        ActivityDetailResponse result = activityService.findById(1L);

        // then
        assertThat(result.getActId()).isEqualTo(1L);
    }

    @Test
    void ID로_활동조회에_실패한다() {
        // given
        ActivityService activityService = new ActivityService(
                new StubEmptyActivityRepository(),
                new QuestionService(
                        new StubQuestionRepository()
                ),
                new ReviewService(
                        new StubExistsUserRepository(),
                        new StubQuestionRepository(),
                        new StubReviewRepository(),
                        new StubHistoryRepository()
                )
        );

        // then
        Assertions.assertThrows(ActivityException.class, () -> {
            // when
            activityService.findById(1L);
        }, ActivityErrorCode.ACTIVITY_NOT_EXISTS.getMsg());
    }
}