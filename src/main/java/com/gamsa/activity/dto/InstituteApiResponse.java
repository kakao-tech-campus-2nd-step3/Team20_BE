package com.gamsa.activity.dto;

import com.gamsa.activity.domain.District;
import com.gamsa.activity.domain.Institute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

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
                .longitude(coordinates.get("longitude"))
                .latitude(coordinates.get("latitude"))
                .phone(phone)
                .build();
    }
}
