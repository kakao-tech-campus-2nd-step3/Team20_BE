package com.gamsa.activity.domain;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class District {

    private int sidoGunguCode;
    private int sidoCode;
    private String sidoName;
    private String gunguName;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private boolean sido;
}
