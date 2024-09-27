package com.gamsa.activity.controller;

import com.gamsa.activity.constant.Category;
import com.gamsa.activity.dto.ActivityDetailResponse;
import com.gamsa.activity.dto.ActivityFilterRequest;
import com.gamsa.activity.dto.ActivityFindSliceResponse;
import com.gamsa.activity.dto.ActivitySaveRequest;
import com.gamsa.activity.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
        @RequestParam(required = false) Category category,
        @RequestParam(defaultValue = "false") boolean teenPossibleOnly,
        @RequestParam(defaultValue = "false") boolean deadlineEndOnly,
        Pageable pageable) {

        ActivityFilterRequest request = new ActivityFilterRequest(category, teenPossibleOnly,
            deadlineEndOnly);
        return activityService.findSlice(request, pageable);
    }

    @GetMapping("{activity-id}")
    public ActivityDetailResponse findById(@PathVariable("activity-id") Long activityId) {
        return activityService.findById(activityId);
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody ActivitySaveRequest saveRequest) {
        activityService.save(saveRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
