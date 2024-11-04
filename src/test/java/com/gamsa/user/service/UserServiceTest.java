package com.gamsa.user.service;

import com.gamsa.avatar.stub.StubEmptyAvatarRepository;
import com.gamsa.avatar.stub.StubExistsAvatarRepository;
import com.gamsa.common.jwt.JwtUtil;
import com.gamsa.user.dto.KakaoLoginResponse;
import com.gamsa.user.dto.KakaoProperties;
import com.gamsa.user.stub.DummyKakaoAccessTokenRepository;
import com.gamsa.user.stub.DummyKakaoLogin;
import com.gamsa.user.stub.StubExistsUserRepository;
import java.util.Map;
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
        Map<String, Object> result = userService.userKakaoLogin("dummyToken");

        String token = (String) result.get("token");
        KakaoLoginResponse response = (KakaoLoginResponse) result.get("body");
        // then
        Assertions.assertThat(token).isNotNull();
        Assertions.assertThat(response.getAvatar()).isNull();
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
        Map<String, Object> result = userService.userKakaoLogin("dummyToken");
        String token = (String) result.get("token");
        KakaoLoginResponse response = (KakaoLoginResponse) result.get("body");
        // then
        Assertions.assertThat(token).isNotNull();
        Assertions.assertThat(response.getAvatar()).isNotNull();
    }
}