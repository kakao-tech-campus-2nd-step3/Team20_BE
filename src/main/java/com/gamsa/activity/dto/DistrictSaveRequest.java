package com.gamsa.activity.dto;

import com.gamsa.activity.domain.District;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class DistrictSaveRequest {

    private final int sidoGunguCode;
    private final int sidoCode;
    private final String sidoName;
    private final String gunguName;
    private final boolean sido;

    public District toModel() {
        return District.builder()
            .sidoGunguCode(sidoGunguCode)
            .sidoCode(sidoCode)
            .sidoName(sidoName)
            .gunguName(gunguName)
            .sido(sido)
            .build();
    }
}
