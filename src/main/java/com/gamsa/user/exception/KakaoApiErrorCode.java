package com.gamsa.user.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum KakaoApiErrorCode {

    // 4xx error
    KAKAO_API_BAD_REQUEST(400, "잘못된 요청"),
    KAKAO_API_UNAUTHORIZED(401, "인증 오류"),
    KAKAO_API_FORBIDDEN(403, "허가되지 않은 접근"),

    // 5xx error
    KAKAO_API_INTERNAL_SERVER_ERROR(500, "서버 내부 오류");

    private final int status;
    private final String msg;
}
