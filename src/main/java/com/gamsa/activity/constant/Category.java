package com.gamsa.activity.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {
    LIFE_SUPPORT_AND_HOUSING_IMPROVEMENT("생활지원 및 주거환경 개선"),
    EDUCATION_AND_MENTORING("교육 및 멘토링"),
    ADMINISTRATIVE_AND_OFFICE_SUPPORT("행정 및 사무지원"),
    CULTURE_ENVIRONMENT_AND_INTERNATIONAL_COOPERATION("문화, 환경 및 국제협력 활동"),
    HEALTHCARE_AND_PUBLIC_WELFARE("보건의료 및 공익활동"),
    COUNSELING_AND_VOLUNTEER_TRAINING("상담 및 자원봉사 교육"),
    OTHER_ACTIVITIES("기타 활동");

    private final String name;

    @JsonCreator
    public static Category fromValues(String value) {
        for (Category category : Category.values()) {
            if (category.getName().equals(value)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }

    public static Category fromValuesForSlice(String value) {
        for (Category category : Category.values()) {
            if (category.getName().equals(value)) {
                return category;
            }
        }
        return null;    // QueryDSL 에서는 null일 경우 필터링에서 제외하므로 null 반환 허용
    }

    @JsonValue
    public String toValue() {
        return this.name;
    }
}
