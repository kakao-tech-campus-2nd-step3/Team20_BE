package com.gamsa.activity.dto;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ActivityFindDistanceOrderRequest {

    private final BigDecimal latitude;
    private final BigDecimal longitude;
    private final int distanceKm;

}
