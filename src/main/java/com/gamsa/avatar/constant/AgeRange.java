package com.gamsa.avatar.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AgeRange {
    TEENAGE("청소년"),
    UNDERGRADUATE("대학생"),
    ADULT("성인");

    private final String name;
}
