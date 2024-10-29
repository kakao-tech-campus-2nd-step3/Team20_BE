package com.gamsa.user.service;

import com.gamsa.avatar.stub.StubEmptyAvatarRepository;
import com.gamsa.avatar.stub.StubExistsAvatarRepository;
import com.gamsa.common.jwt.JwtUtil;
import com.gamsa.user.dto.KakaoLoginResponse;
import com.gamsa.user.dto.KakaoProperties;
import com.gamsa.user.stub.DummyKakaoAccessTokenRepository;
import com.gamsa.user.stub.DummyKakaoLogin;
import com.gamsa.user.stub.StubExistsUserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserServiceTest {

    private final String dummySecretKey = "sghdfdfsfskwpqdnblkdjofknvboiwrbnowagibsdhgalkshgowaweqnbzoiwnyzbvwow";

    @Test
    @DisplayName("아직 아바타가 없는 유저 카카오 로그인 성공")
    void avatarEmptyUserKakaoLogin() {
        // given
        final UserService userService = new UserService(
            new JwtUtil(dummySecretKey),
            new DummyKakaoLogin(new KakaoProperties("dummyUrl")),
            new StubExistsUserRepository(),
            new StubEmptyAvatarRepository(),      // 아바타 X
            new DummyKakaoAccessTokenRepository());
        // when
        KakaoLoginResponse response = userService.userKakaoLogin("dummyToken");
        // then
        Assertions.assertThat(response.getToken()).isNotNull();
        Assertions.assertThat(response.isAvatarExists()).isFalse();
    }

    @Test
    @DisplayName("아바타가 존재하는 유저 카카오 로그인 성공")
    void avatarExistsUserKakaoLogin() {
        // given
        final UserService userService = new UserService(
            new JwtUtil(dummySecretKey),
            new DummyKakaoLogin(new KakaoProperties("dummyUrl")),
            new StubExistsUserRepository(),
            new StubExistsAvatarRepository(),      // 아바타 O
            new DummyKakaoAccessTokenRepository());
        // when
        KakaoLoginResponse response = userService.userKakaoLogin("dummyToken");
        // then
        Assertions.assertThat(response.getToken()).isNotNull();
        Assertions.assertThat(response.isAvatarExists()).isTrue();
    }
}