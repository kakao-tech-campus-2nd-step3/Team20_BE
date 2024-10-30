package com.gamsa.user.service;

import com.gamsa.avatar.domain.Avatar;
import com.gamsa.avatar.dto.AvatarFindResponse;
import com.gamsa.avatar.repository.AvatarRepository;
import com.gamsa.common.jwt.JwtUtil;
import com.gamsa.user.domain.KakaoLogin;
import com.gamsa.user.domain.User;
import com.gamsa.user.dto.KakaoLoginResponse;
import com.gamsa.user.dto.KakaoUserInfoResponse;
import com.gamsa.user.repository.KakaoAccessTokenRepository;
import com.gamsa.user.repository.UserRepository;
import java.util.Map;
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
    private final AvatarRepository avatarRepository;
    private final KakaoAccessTokenRepository kakaoAccessTokenRepository;

    public Map<String, Object> userKakaoLogin(String kakaoToken) {
        KakaoUserInfoResponse userInfo = kakaoLogin.getUserInfo(kakaoToken);
        Optional<User> user = userRepository.findById(userInfo.getId());

        if (user.isEmpty()) {
            userRepository.save(generateNewUser(userInfo));
        }
        kakaoAccessTokenRepository.save(userInfo.getId(), kakaoToken);

        Optional<Avatar> avatar = avatarRepository.findByUserId(userInfo.getId());
        KakaoLoginResponse body = KakaoLoginResponse.builder()
            .avatar(avatar.map(AvatarFindResponse::from).orElse(null))
            .build();

        return Map.of(
            "token", (Object) jwtUtil.createJwt(userInfo.getId(), TOKEN_EXPIRED_TIME),
            "body", (Object) body
        );
    }

    private User generateNewUser(KakaoUserInfoResponse userInfo) {
        return User.builder()
            .id(userInfo.getId())
            .nickname(userInfo.getNickname())
            .build();
    }
}
