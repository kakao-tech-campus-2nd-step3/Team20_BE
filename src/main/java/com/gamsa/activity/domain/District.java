package com.gamsa.activity.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class District {

    private int sidoCode;
    private int sidoGunguCode;
    private String sidoName;
    private String gunguName;
    private boolean sido;
}
