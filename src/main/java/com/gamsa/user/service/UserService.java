package com.gamsa.user.service;

import com.gamsa.common.jwt.JwtUtil;
import com.gamsa.user.domain.KakaoLogin;
import com.gamsa.user.dto.KakaoUserInfoResponse;
import com.gamsa.user.entity.UserJpaEntity;
import com.gamsa.user.repository.KakaoAccessTokenRepository;
import com.gamsa.user.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    @Value("${spring.jwt.expiration-time}")
    private long TOKEN_EXPIRED_TIME;
    private final JwtUtil jwtUtil;
    private final KakaoLogin kakaoLogin;
    private final UserRepository userRepository;
    private final KakaoAccessTokenRepository kakaoAccessTokenRepository;

    public String userKakaoLogin(String kakaoToken) {
        KakaoUserInfoResponse userInfo = kakaoLogin.getUserInfo(kakaoToken);
        Optional<UserJpaEntity> user = userRepository.findById(userInfo.getId());

        if (user.isEmpty()) {
            userRepository.save(generateNewUser(userInfo));
            // Todo 아바타 생성
        }
        kakaoAccessTokenRepository.save(userInfo.getId(), kakaoToken);

        return jwtUtil.createJwt(userInfo.getId(), TOKEN_EXPIRED_TIME);
    }

    private UserJpaEntity generateNewUser(KakaoUserInfoResponse userInfo) {
        return UserJpaEntity.builder()
            .id(userInfo.getId())
            .nickname(userInfo.getNickname())
            .build();
    }
}
