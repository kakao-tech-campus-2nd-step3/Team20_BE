package com.gamsa.activity.controller;

import com.gamsa.activity.constant.Category;
import com.gamsa.activity.dto.ActivityDetailResponse;
import com.gamsa.activity.dto.ActivityFilterRequest;
import com.gamsa.activity.dto.ActivityFindSliceResponse;
import com.gamsa.activity.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

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
            Pageable pageable) {

        ActivityFilterRequest request = ActivityFilterRequest.builder()
                .category(Category.fromValuesForSlice(category))
                .sidoGunguCode(sidoGunguCode)
                .sidoCode(sidoCode)
                .teenPossibleOnly(teenPossibleOnly)
                .beforeDeadlineOnly(beforeDeadlineOnly)
                .build();

        return activityService.findSlice(request, pageable);
    }

    @GetMapping("{activity-id}")
    public ActivityDetailResponse findById(@PathVariable("activity-id") Long activityId) {
        return activityService.findById(activityId);
    }
}
