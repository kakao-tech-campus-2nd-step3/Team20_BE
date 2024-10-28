package com.gamsa.user.domain;

import com.gamsa.common.exception.RestClientErrorHandler;
import com.gamsa.user.dto.KakaoProperties;
import com.gamsa.user.dto.KakaoUserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
@Component
public class KakaoLogin {

    private final KakaoProperties kakaoProperties;
    private final RestClient restClient = RestClient.create();

    public KakaoUserInfoResponse getUserInfo(String token) {
        return restClient.post()
            .uri(kakaoProperties.getUserInfoUrl())
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .header("Authorization", "Bearer " + token)
            .retrieve()
            .onStatus(HttpStatusCode::is4xxClientError, RestClientErrorHandler.http4xxErrorHandler)
            .onStatus(HttpStatusCode::is5xxServerError, RestClientErrorHandler.http5xxErrorHandler)
            .body(KakaoUserInfoResponse.class);
    }
}
