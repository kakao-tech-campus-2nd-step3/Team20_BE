package com.gamsa.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class KakaoLoginResponse {

    private final String token;
    private final boolean avatarExists;
}
