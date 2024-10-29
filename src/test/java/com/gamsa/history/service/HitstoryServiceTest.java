package com.gamsa.history.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.gamsa.activity.stub.StubExistsActivityRepository;
import com.gamsa.avatar.stub.StubExistsAvatarRepository;
import com.gamsa.history.dto.HistorySaveRequest;
import com.gamsa.history.stub.StubHistoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class HitstoryServiceTest {

    HistorySaveRequest historySaveRequest = HistorySaveRequest.builder()
            .actId(1L)
            .avatarId(1L)
            .build();

    @Test
    void 새로운_기록_저장() {
        //given
        HistoryService historyService = new HistoryService(new StubHistoryRepository(),
            new StubExistsAvatarRepository(), new StubExistsActivityRepository());

        //when & then
        assertDoesNotThrow(() -> historyService.save(historySaveRequest));
    }

    @Test
    void 유저_기록_찾기() {
        //given
        HistoryService historyService = new HistoryService(new StubHistoryRepository(),
            new StubExistsAvatarRepository(), new StubExistsActivityRepository());

        //when & then
        Pageable pageable = PageRequest.of(0, 10);
        assertThat(historyService.findSliceByAvatarId(1L, pageable)).isNotNull();
    }

    @Test
    void 기록_삭제() {
        //given
        HistoryService historyService = new HistoryService(new StubHistoryRepository(),
            new StubExistsAvatarRepository(), new StubExistsActivityRepository());

        //when & then
        assertDoesNotThrow(() -> historyService.delete(1L));
    }

    @Test
    void 리뷰_상태_업데이트() {
        //given
        HistoryService historyService = new HistoryService(new StubHistoryRepository(),
            new StubExistsAvatarRepository(), new StubExistsActivityRepository());

        //when & then
        assertDoesNotThrow(() -> historyService.updateReviewed(1L, true));
    }
}
