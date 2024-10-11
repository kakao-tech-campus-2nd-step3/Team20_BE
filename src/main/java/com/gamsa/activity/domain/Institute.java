package com.gamsa.activity.domain;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Institute {

    private Long instituteId;
    private String name;
    private String location;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private District sidoGungu;
    private String phone;
}
