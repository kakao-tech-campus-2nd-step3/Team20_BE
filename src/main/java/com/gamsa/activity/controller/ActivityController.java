package com.gamsa.activity.controller;

import com.gamsa.activity.dto.ActivityDetailResponse;
import com.gamsa.activity.dto.ActivityFindAllResponse;
import com.gamsa.activity.dto.ActivitySaveRequest;
import com.gamsa.activity.service.ActivityService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/programs")
public class ActivityController {

    private final ActivityService activityService;

    @GetMapping
    public List<ActivityFindAllResponse> findAll() {
        return activityService.findAll();
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
