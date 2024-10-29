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
        final UserService userService = UserService.builder()
            .jwtUtil(new JwtUtil(dummySecretKey))
            .kakaoLogin(new DummyKakaoLogin(new KakaoProperties("dummyUrl")))
            .userRepository(new StubExistsUserRepository())
            .avatarRepository(new StubEmptyAvatarRepository())      // 아바타 X
            .kakaoAccessTokenRepository(new DummyKakaoAccessTokenRepository())
            .build();
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
        final UserService userService = UserService.builder()
            .jwtUtil(new JwtUtil(dummySecretKey))
            .kakaoLogin(new DummyKakaoLogin(new KakaoProperties("dummyUrl")))
            .userRepository(new StubExistsUserRepository())
            .avatarRepository(new StubExistsAvatarRepository())      // 아바타 O
            .kakaoAccessTokenRepository(new DummyKakaoAccessTokenRepository())
            .build();
        // when
        KakaoLoginResponse response = userService.userKakaoLogin("dummyToken");
        // then
        Assertions.assertThat(response.getToken()).isNotNull();
        Assertions.assertThat(response.isAvatarExists()).isTrue();
    }
}