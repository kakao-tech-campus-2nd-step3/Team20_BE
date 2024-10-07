package com.gamsa.activity.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Institute {

    private Long instituteId;
    private String name;
    private String location;
    private long latitude;
    private long longitude;
    // Todo 시군구 코드
    private String phone;
}
