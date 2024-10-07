package com.gamsa.avatar.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.gamsa.avatar.constant.AgeRange;
import com.gamsa.avatar.constant.Experienced;
import com.gamsa.avatar.dto.AvatarSaveRequest;
import com.gamsa.avatar.stub.StubAvatarRepository;
import org.junit.jupiter.api.Test;

public class AvatarServiceTest {
    AvatarSaveRequest saveRequest = AvatarSaveRequest.builder()
            .nickname("닉네임")
            .ageRange(AgeRange.ADULT)
            .experienced(Experienced.EXPERT)
            .build();

    @Test
    void 새로운_유저_저장() {
        //given
        AvatarService avatarService = new AvatarService(new StubAvatarRepository());

        //then
        assertDoesNotThrow(() -> avatarService.save(saveRequest));
    }

    @Test
    void 기존_유저_검색() {
        //given
        AvatarService avatarService = new AvatarService(new StubAvatarRepository());

        //then
        assertThat(avatarService.findById(1L)).isNotNull();
    }

    @Test
    void 기존_유저_삭제() {
        //given
        AvatarService avatarService = new AvatarService(new StubAvatarRepository());

        //then
        assertDoesNotThrow(() -> avatarService.delete(1L));
    }



    @Test
    void 기존_유저_업데이트() {
        //given
        AvatarService avatarService = new AvatarService(new StubAvatarRepository());

        //then
        assertDoesNotThrow(() -> avatarService.save(saveRequest));
    }
}
