package com.gamsa.activity.service;

import com.gamsa.activity.constant.ActivityErrorCode;
import com.gamsa.activity.domain.Activity;
import com.gamsa.activity.dto.ActivityDetailResponse;
import com.gamsa.activity.dto.ActivityFindSliceResponse;
import com.gamsa.activity.dto.ActivitySaveRequest;
import com.gamsa.activity.exception.ActivityException;
import com.gamsa.activity.repository.ActivityRepository;
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
                throw new ActivityException(ActivityErrorCode.ACTIVITY_ALREADY_EXISTS);
            });
        activityRepository.save(saveRequest.toModel());
    }

    public Slice<ActivityFindSliceResponse> findSlice(Pageable pageable) {
        Slice<Activity> activities = activityRepository.findSlice(pageable);
        return activities.map(ActivityFindSliceResponse::from);
    }

    public ActivityDetailResponse findById(Long activityId) {
        Activity activity = activityRepository.findById(activityId)
            .orElseThrow(() -> new ActivityException(ActivityErrorCode.ACTIVITY_NOT_EXISTS));
        return ActivityDetailResponse.from(activity);
    }
}
