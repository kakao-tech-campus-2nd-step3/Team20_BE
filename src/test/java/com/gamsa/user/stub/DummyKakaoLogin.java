package com.gamsa.user.stub;

import com.gamsa.user.domain.KakaoLogin;
import com.gamsa.user.dto.KakaoProperties;
import com.gamsa.user.dto.KakaoUserInfoResponse;

public class DummyKakaoLogin extends KakaoLogin {

    public DummyKakaoLogin(KakaoProperties kakaoProperties) {
        super(kakaoProperties);
    }

    @Override
    public KakaoUserInfoResponse getUserInfo(String token) {
        return new KakaoUserInfoResponse();
    }
}
