package com.gamsa.activity.dto;

import com.gamsa.activity.domain.District;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
public class DistrictSaveRequest {

    private final int sidoGunguCode;
    private final int sidoCode;
    private final String sidoName;
    private final String gunguName;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private final boolean sido;

    public District toModel() {
        return District.builder()
                .sidoGunguCode(sidoGunguCode)
                .sidoCode(sidoCode)
                .sidoName(sidoName)
                .gunguName(gunguName)
                .latitude(latitude)
                .longitude(longitude)
                .sido(sido)
                .build();
    }
}
