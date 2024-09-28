package com.gamsa.avatar.constant;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class AgeRangeConverter implements AttributeConverter<AgeRange, String> {
    @Override
    public String convertToDatabaseColumn(AgeRange attribute) {
        return attribute.toString();
    }

    @Override
    public AgeRange convertToEntityAttribute(String dbData) {
        return AgeRange.valueOf(dbData);
    }
}
