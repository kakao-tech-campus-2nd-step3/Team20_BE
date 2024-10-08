package com.gamsa.activity.dto;

import com.gamsa.activity.domain.Institute;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class InstituteDetailResponse {

    private final Long instituteId;
    private final String name;
    private final String location;
    private final BigDecimal latitude;
    private final BigDecimal longitude;
    // Todo 시군구 코드
    private final String phone;

    public static InstituteDetailResponse from(Institute institute) {
        return InstituteDetailResponse.builder()
            .instituteId(institute.getInstituteId())
            .name(institute.getName())
            .location(institute.getLocation())
            .latitude(institute.getLatitude())
            .longitude(institute.getLongitude())
            .phone(institute.getPhone())
            .build();
    }
}
