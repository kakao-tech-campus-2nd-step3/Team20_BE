package com.gamsa.activity.exception;

import com.gamsa.activity.constant.ActivityErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ActivityException extends RuntimeException {

    private final ActivityErrorCode activityErrorCode;
}
