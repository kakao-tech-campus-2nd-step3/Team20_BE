package com.gamsa.common.exception;

import com.gamsa.activity.exception.ActivityCustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ActivityCustomException.class)
    private ResponseEntity<?> ActivityCustomExceptionHandler(ActivityCustomException e) {
        log.error(String.valueOf(e.getStackTrace()[0]));
        return ResponseEntity
            .status(e.getActivityErrorCode().getStatus())
            .body(e.getActivityErrorCode().getMsg());
    }
}
