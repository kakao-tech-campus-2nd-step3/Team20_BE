package com.gamsa.activity.dto;

import com.gamsa.activity.domain.Institute;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class InstituteSaveRequest {

    private String name;
    private String location;
    private long latitude;
    private long longitude;
    // Todo 시군구 코드
    private String phone;

    public Institute toModel() {
        return Institute.builder()
            .name(name)
            .location(location)
            .latitude(latitude)
            .longitude(longitude)
            .phone(phone)
            .build();
    }
}
