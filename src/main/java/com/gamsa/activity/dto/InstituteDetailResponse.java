package com.gamsa.activity.dto;

import com.gamsa.activity.domain.Institute;
import com.gamsa.review.dto.QuestionResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Builder
@RequiredArgsConstructor
public class InstituteDetailResponse {

    private final Long instituteId;
    private final String name;
    private final String location;
    private final BigDecimal latitude;
    private final BigDecimal longitude;
    private final String phone;
    private final Map<QuestionResponse, BigDecimal> scores;

    public static InstituteDetailResponse from(Institute institute, Map<QuestionResponse, BigDecimal> scores) {
        return InstituteDetailResponse.builder()
                .instituteId(institute.getInstituteId())
                .name(institute.getName())
                .location(institute.getLocation())
                .latitude(institute.getLatitude())
                .longitude(institute.getLongitude())
                .phone(institute.getPhone())
                .scores(scores)
                .build();
    }
}
