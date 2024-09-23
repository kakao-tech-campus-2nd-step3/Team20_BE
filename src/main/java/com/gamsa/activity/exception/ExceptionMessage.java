package com.gamsa.activity.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionMessage {

    ACTIVITY_ALREADY_EXISTS("활동이 이미 존재합니다."),
    ACTIVITY_NOT_EXISTS("존재하지 않는 활동입니다.");

    private final String msg;
}
