package com.gamsa.avatar.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.gamsa.avatar.constant.AgeRange;
import com.gamsa.avatar.constant.Experienced;
import com.gamsa.avatar.dto.AvatarSaveRequest;
import com.gamsa.avatar.stub.StubEmptyAvatarRepository;
import com.gamsa.avatar.stub.StubExistsAvatarRepository;
import com.gamsa.user.domain.User;
import com.gamsa.user.stub.StubExistsUserRepository;
import org.junit.jupiter.api.Test;

public class AvatarServiceTest {

    private final AvatarSaveRequest saveRequest = AvatarSaveRequest.builder()
            .nickname("닉네임")
            .ageRange(AgeRange.ADULT)
            .experienced(Experienced.EXPERT)
            .build();

    private final User user = User.builder()
        .id(1L)
        .nickname("nickname")
        .build();

    @Test
    void 새로운_아바타_저장() {
        //given
        AvatarService avatarService = new AvatarService(
            new StubEmptyAvatarRepository(),
            new StubExistsUserRepository());

        //then
        assertDoesNotThrow(() -> avatarService.save(saveRequest, user.getId()));
    }

    @Test
    void 기존_아바타_검색_성공() {
        //given
        AvatarService avatarService = new AvatarService(
            new StubExistsAvatarRepository(),
            new StubExistsUserRepository());

        //then
        assertThat(avatarService.findByUserId(1L)).isNotNull();
    }

    @Test
    void 기존_유저_삭제() {
        //given
        AvatarService avatarService = new AvatarService(
            new StubExistsAvatarRepository(),
            new StubExistsUserRepository());

        //then
        assertDoesNotThrow(() -> avatarService.delete(1L));
    }



    @Test
    void 기존_유저_업데이트() {
        //given
        AvatarService avatarService = new AvatarService(
            new StubEmptyAvatarRepository(),
            new StubExistsUserRepository());

        AvatarSaveRequest updateRequest = AvatarSaveRequest.builder()
            .nickname("새 닉네임")
            .ageRange(AgeRange.ADULT)
            .experienced(Experienced.EXPERT)
            .build();

        //then
        assertDoesNotThrow(() -> avatarService.save(updateRequest, user.getId()));
    }
}
