package com.gamsa.avatar.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AgeRange {
    TEENAGE("청소년"),
    UNDERGRADUATE("대학생"),
    ADULT("성인");

    private final String name;

    @JsonCreator
    public static AgeRange fromValue(String value) {
        for (AgeRange ageRange : AgeRange.values()) {
            if (ageRange.name.equals(value)) {
                return ageRange;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }

    @JsonValue
    public String toValue() {
        return this.name;
    }
}
