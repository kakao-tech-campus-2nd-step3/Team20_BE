package com.gamsa.user.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KakaoUserInfoResponse {

    private Long id;
    private KakaoAccount kakaoAccount;

    public String getNickname() {
        return kakaoAccount.getProfile().getNickname();
    }

    @Getter
    @NoArgsConstructor
    private static class KakaoAccount {

        private Profile profile;
    }

    @Getter
    @NoArgsConstructor
    private static class Profile {

        private String nickname;
    }
}

