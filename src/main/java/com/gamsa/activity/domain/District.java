package com.gamsa.activity.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class District {

    private int sidoCode;
    private int gunguCode;
    private String sidoName;
    private String gunguName;
    private boolean sido;
}
