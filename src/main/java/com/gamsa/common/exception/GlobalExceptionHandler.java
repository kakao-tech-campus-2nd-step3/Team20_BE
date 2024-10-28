package com.gamsa.common.exception;

import com.gamsa.activity.exception.ActivityException;
import com.gamsa.user.exception.KakaoApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ActivityException.class)
    private ResponseEntity<?> ActivityCustomExceptionHandler(ActivityException e) {
        log.error(String.valueOf(e.getStackTrace()[0]));
        return ResponseEntity
            .status(e.getActivityErrorCode().getStatus())
            .body(e.getActivityErrorCode().getMsg());
    }

    @ExceptionHandler(KakaoApiException.class)
    private ResponseEntity<?> kakaoApiExceptionHandler(KakaoApiException e) {
        log.error(String.valueOf(e.getStackTrace()[0]));
        return ResponseEntity
            .status(e.getKakaoAPIErrorCode().getStatus())
            .body(e.getKakaoAPIErrorCode().getMsg());
    }
}
