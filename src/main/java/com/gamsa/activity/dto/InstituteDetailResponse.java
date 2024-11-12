package com.gamsa.activity.dto;

import com.gamsa.activity.domain.Institute;
import com.gamsa.review.domain.Question;
import com.gamsa.review.dto.ReviewResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    private final List<ReviewResponse> scores;

    public static InstituteDetailResponse from(Institute institute, Map<Question, BigDecimal> scores) {
        List<ReviewResponse> reviewResponses = scores.entrySet().stream()
                .map(entry -> ReviewResponse.from(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());


        return InstituteDetailResponse.builder()
                .instituteId(institute.getInstituteId())
                .name(institute.getName())
                .location(institute.getLocation())
                .latitude(institute.getLatitude())
                .longitude(institute.getLongitude())
                .phone(institute.getPhone())
                .scores(reviewResponses)
                .build();
    }
}
