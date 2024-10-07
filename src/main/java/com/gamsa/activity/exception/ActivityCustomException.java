package com.gamsa.activity.exception;

import com.gamsa.common.constant.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ActivityCustomException extends RuntimeException {

    private final ErrorCode errorCode;
}
