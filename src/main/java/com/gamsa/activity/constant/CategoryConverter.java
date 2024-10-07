package com.gamsa.activity.constant;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.stream.Stream;

@Converter
public class CategoryConverter implements AttributeConverter<Category, String> {

    @Override
    public String convertToDatabaseColumn(Category category) {
        if (category == null) {
            return null;
        }
        return category.getName();
    }

    @Override
    public Category convertToEntityAttribute(String name) {
        if (name == null) {
            return null;
        }
        return Stream.of(Category.values())
            .filter(category -> category.getName().equals(name))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리 접근."));
    }
}
