package com.gamsa.avatar.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Experienced {
    NOVICE("초심자"),
    INTERMIDIATE("중급자"),
    EXPERT("상급자");

    private final String name;
}
