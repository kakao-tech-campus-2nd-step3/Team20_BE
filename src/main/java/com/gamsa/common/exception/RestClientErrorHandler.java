package com.gamsa.common.exception;

import com.gamsa.user.exception.KakaoApiErrorCode;
import com.gamsa.user.exception.KakaoApiException;
import org.springframework.web.client.RestClient.ResponseSpec.ErrorHandler;

public class RestClientErrorHandler {

    public static ErrorHandler http4xxErrorHandler = (request, response) -> {
        switch (response.getStatusCode().value()) {
            case 400:
                throw new KakaoApiException(KakaoApiErrorCode.KAKAO_API_BAD_REQUEST);
            case 401:
                throw new KakaoApiException(KakaoApiErrorCode.KAKAO_API_UNAUTHORIZED);
            case 403:
                throw new KakaoApiException(KakaoApiErrorCode.KAKAO_API_FORBIDDEN);
        }
    };

    public static ErrorHandler http5xxErrorHandler = (request, response) -> {
        switch (response.getStatusCode().value()) {
            case 500:
                throw new KakaoApiException(KakaoApiErrorCode.KAKAO_API_INTERNAL_SERVER_ERROR);
        }
    };

}
