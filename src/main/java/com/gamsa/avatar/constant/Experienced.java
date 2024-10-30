package com.gamsa.avatar.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Experienced {
    NOVICE("초심자"),
    INTERMEDIATE("중급자"),
    EXPERT("상급자");

    private final String name;

    @JsonCreator
    public static Experienced fromValue(String value) {
        for (Experienced level : Experienced.values()) {
            if (level.name.equals(value)) {
                return level;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }

    @JsonValue
    public String toValue() {
        return this.name;
    }
}
