package com.gamsa.user.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class KakaoApiException extends RuntimeException {

    private final KakaoApiErrorCode kakaoAPIErrorCode;
}
