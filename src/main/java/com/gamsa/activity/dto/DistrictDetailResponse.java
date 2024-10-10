package com.gamsa.activity.dto;

import com.gamsa.activity.domain.District;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class DistrictDetailResponse {

    private final int sidoGunguCode;
    private final int sidoCode;
    private final String sidoName;
    private final String gunguName;
    private final boolean sido;

    public static DistrictDetailResponse from(District district) {
        return DistrictDetailResponse.builder()
            .sidoGunguCode(district.getSidoGunguCode())
            .sidoCode(district.getSidoCode())
            .sidoName(district.getSidoName())
            .gunguName(district.getGunguName())
            .sido(district.isSido())
            .build();
    }
}
