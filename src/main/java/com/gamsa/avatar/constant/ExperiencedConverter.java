package com.gamsa.avatar.constant;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ExperiencedConverter implements AttributeConverter<Experienced, String> {
    @Override
    public String convertToDatabaseColumn(Experienced attribute) {
        return attribute.toString();
    }

    @Override
    public Experienced convertToEntityAttribute(String dbData) {
        return Experienced.valueOf(dbData);
    }
}
