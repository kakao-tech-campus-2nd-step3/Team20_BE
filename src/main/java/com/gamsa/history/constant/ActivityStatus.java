package com.gamsa.history.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ActivityStatus {
    APPLIED("접수"),
    WAITING("활동 대기"),
    ACT("활동"),
    FINISHED("활동 완료"),
    REVIEWED("리뷰 완료");

    private final String name;
}
