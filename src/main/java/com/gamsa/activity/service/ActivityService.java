package com.gamsa.activity.service;

import com.gamsa.activity.domain.Activity;
import com.gamsa.activity.dto.ActivityDetailResponse;
import com.gamsa.activity.dto.ActivityFindAllResponse;
import com.gamsa.activity.dto.ActivitySaveRequest;
import com.gamsa.activity.exception.ActivityCustomException;
import com.gamsa.activity.repository.ActivityRepository;
import com.gamsa.common.constant.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ActivityService {

    private final ActivityRepository activityRepository;

    public void save(ActivitySaveRequest saveRequest) {
        activityRepository.findById(saveRequest.getActId())
            .ifPresent(activity -> {
                throw new ActivityCustomException(ErrorCode.ACTIVITY_ALREADY_EXISTS);
            });
        activityRepository.save(saveRequest.toModel());
    }

    public Slice<ActivityFindAllResponse> findAll(Pageable pageable) {
        Slice<Activity> activities = activityRepository.findSlice(pageable);
        return activities.map(ActivityFindAllResponse::from);
    }

    public ActivityDetailResponse findById(Long activityId) {
        Activity activity = activityRepository.findById(activityId)
            .orElseThrow(() -> new ActivityCustomException(ErrorCode.ACTIVITY_NOT_EXISTS));
        return ActivityDetailResponse.from(activity);
    }
}
