package com.gamsa.activity.dto;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class ActivityFindDistanceOrderRequest {

    private final BigDecimal latitude;
    private final BigDecimal longitude;
    private final int distanceKm;

}
