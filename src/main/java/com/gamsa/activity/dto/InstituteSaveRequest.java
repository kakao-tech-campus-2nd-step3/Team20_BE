package com.gamsa.activity.dto;

import com.gamsa.activity.domain.Institute;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class InstituteSaveRequest {

    private String name;
    private String location;
    private BigDecimal latitude;
    private BigDecimal longitude;
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
