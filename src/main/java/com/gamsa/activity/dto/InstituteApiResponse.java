package com.gamsa.activity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Builder
@AllArgsConstructor
public class InstituteApiResponse {

    private String name;
    private String location;
    private int sidoCode;
    private int sidoGunguCode;
    private String phone;

    public InstituteSaveRequest toSaveRequest(Map<String, BigDecimal> coordinates) {

        return InstituteSaveRequest.builder()
                .name(name)
                .location(location)
                .sidoCode(sidoCode)
                .sidoGunguCode(sidoGunguCode)
                .longitude(coordinates.getOrDefault("longitude", new BigDecimal(1)))
                .latitude(coordinates.getOrDefault("latitude", new BigDecimal(1)))
                .phone(phone)
                .build();
    }
}
