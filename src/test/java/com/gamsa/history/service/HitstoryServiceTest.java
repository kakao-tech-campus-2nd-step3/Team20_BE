package com.gamsa.history.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.gamsa.activity.stub.StubExistsActivityRepository;
import com.gamsa.avatar.stub.StubExistsAvatarRepository;
import com.gamsa.history.dto.HistorySaveRequest;
import com.gamsa.history.stub.StubHistoryRepository;
import com.gamsa.review.service.QuestionService;
import com.gamsa.review.service.ReviewService;
import com.gamsa.review.stub.StubAnswerRepository;
import com.gamsa.review.stub.StubQuestionRepository;
import com.gamsa.review.stub.StubReviewRepository;
import com.gamsa.user.stub.StubExistsUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class HitstoryServiceTest {

    HistorySaveRequest historySaveRequest = HistorySaveRequest.builder()
            .actId(1L)
            .build();

    ReviewService reviewService = new ReviewService(
            new StubExistsUserRepository(),
            new StubQuestionRepository(),
            new StubReviewRepository(),
            new StubAnswerRepository(),
            new StubHistoryRepository()
    );

    QuestionService questionService = new QuestionService(
            new StubQuestionRepository()
    );

    HistoryService historyService = new HistoryService(new StubHistoryRepository(),
            new StubExistsAvatarRepository(), new StubExistsActivityRepository(), questionService, reviewService);

    @Test
    void 새로운_기록_저장() {
        //when & then
        assertDoesNotThrow(() -> historyService.save(historySaveRequest, 1L));
    }

    @Test
    void 유저_기록_찾기() {

        //when & then
        Pageable pageable = PageRequest.of(0, 10);
        assertThat(historyService.findSliceByAvatarId(1L, pageable)).isNotNull();
    }

    @Test
    void 기록_삭제() {
        //when & then
        assertDoesNotThrow(() -> historyService.delete(1L));
    }

    @Test
    void 리뷰_상태_업데이트() {
        //when & then
        assertDoesNotThrow(() -> historyService.updateReviewed(1L, true));
    }
}
