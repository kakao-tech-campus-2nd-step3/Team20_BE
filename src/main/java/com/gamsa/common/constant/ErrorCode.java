package com.gamsa.common.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // 404 Not Found : 존재하지 않는 리소스 접근
    ACTIVITY_NOT_EXISTS(404, "존재하지 않는 활동입니다."),

    // 409 Conflict : 요청이 현재 서버의 상태와 충돌
    ACTIVITY_ALREADY_EXISTS(409, "이미 존재하는 활동입니다.");

    private final int status;
    private final String msg;
}
