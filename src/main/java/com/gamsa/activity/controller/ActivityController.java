package com.gamsa.activity.controller;

import com.gamsa.activity.constant.Category;
import com.gamsa.activity.dto.ActivityDetailResponse;
import com.gamsa.activity.dto.ActivityFilterRequest;
import com.gamsa.activity.dto.ActivityFindDistanceOrderRequest;
import com.gamsa.activity.dto.ActivityFindSliceResponse;
import com.gamsa.activity.service.ActivityService;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/activities")
public class ActivityController {

    private final ActivityService activityService;

    @GetMapping
    public Slice<ActivityFindSliceResponse> findSlice(
        @RequestParam(required = false) String category,
        @RequestParam(required = false) Integer sidoGunguCode,
        @RequestParam(required = false) Integer sidoCode,
        @RequestParam(defaultValue = "false") boolean teenPossibleOnly,
        @RequestParam(defaultValue = "false") boolean beforeDeadlineOnly,
        @RequestParam(required = false) String keyword,
        @RequestParam(required = false) BigDecimal latitude,
        @RequestParam(required = false) BigDecimal longitude,
        @RequestParam(required = false, defaultValue = "10") Integer distanceKm,
        Pageable pageable) {

        // 필터링, 검색 관련 정보
        ActivityFilterRequest filterRequest = ActivityFilterRequest.builder()
            .category(Category.fromValuesForSlice(category))
            .sidoGunguCode(sidoGunguCode)
            .sidoCode(sidoCode)
            .teenPossibleOnly(teenPossibleOnly)
            .beforeDeadlineOnly(beforeDeadlineOnly)
            .keyword(keyword)
            .build();

        // 가까운 거리순 정렬 관련 정보
        ActivityFindDistanceOrderRequest distanceOrderRequest = ActivityFindDistanceOrderRequest.builder()
            .latitude(latitude)
            .longitude(longitude)
            .distanceKm(distanceKm)
            .build();

        return activityService.findSlice(filterRequest, distanceOrderRequest, pageable);
    }

    @GetMapping("{activity-id}")
    public ActivityDetailResponse findById(@PathVariable("activity-id") Long activityId) {
        return activityService.findById(activityId);
    }
}
