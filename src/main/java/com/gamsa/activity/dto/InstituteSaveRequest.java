package com.gamsa.activity.dto;

import com.gamsa.activity.domain.District;
import com.gamsa.activity.domain.Institute;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
public class InstituteSaveRequest {

    private String name;
    private String location;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private int sidoCode;
    private int sidoGunguCode;
    private String phone;

    public Institute toModel(District district) {
        return Institute.builder()
            .name(name)
            .location(location)
            .latitude(latitude)
            .longitude(longitude)
            .sidoGungu(district)
            .phone(phone)
            .build();
    }
}
