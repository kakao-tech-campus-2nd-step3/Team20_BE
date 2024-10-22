package com.gamsa.history.constant;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter
public class ActivityStatusConverter implements AttributeConverter<ActivityStatus, String> {

    @Override
    public String convertToDatabaseColumn(ActivityStatus activityStatus) {
        if (activityStatus == null) {
            return null;
        }
        return activityStatus.getName();
    }

    @Override
    public ActivityStatus convertToEntityAttribute(String name) {
        if (name == null) {
            return null;
        }
        return Stream.of(ActivityStatus.values())
                .filter(category -> category.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 활동 상태 접근."));
    }
}
