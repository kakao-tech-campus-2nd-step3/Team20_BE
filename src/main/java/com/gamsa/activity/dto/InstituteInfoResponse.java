package com.gamsa.activity.dto;

import com.gamsa.activity.domain.Institute;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class InstituteInfoResponse {

    private final Long instituteId;
    private final String name;
    private final String location;
    private final long latitude;
    private final long longitude;
    // Todo 시군구 코드
    private final String phone;

    public static InstituteInfoResponse from(Institute institute) {
        return InstituteInfoResponse.builder()
            .instituteId(institute.getInstituteId())
            .name(institute.getName())
            .location(institute.getLocation())
            .latitude(institute.getLatitude())
            .longitude(institute.getLongitude())
            .phone(institute.getPhone())
            .build();
    }
}
