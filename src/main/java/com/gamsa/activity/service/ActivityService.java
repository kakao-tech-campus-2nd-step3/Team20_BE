package com.gamsa.activity.service;

import com.gamsa.activity.domain.Activity;
import com.gamsa.activity.dto.ActivityDetailResponse;
import com.gamsa.activity.dto.ActivityFindAllResponse;
import com.gamsa.activity.dto.ActivitySaveRequest;
import com.gamsa.activity.exception.ActivityAlreadyExistsException;
import com.gamsa.activity.repository.ActivityRepository;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ActivityService {

    private final ActivityRepository activityRepository;

    public void save(ActivitySaveRequest saveRequest) {
        Activity activity = activityRepository.findById(saveRequest.getActId())
            .orElseThrow(() -> new ActivityAlreadyExistsException("이미 활동이 존재합니다."));
        activityRepository.save(activity);
    }

    public List<ActivityFindAllResponse> findAll() {
        List<Activity> activities = activityRepository.findAll();
        return activities.stream()
            .map(ActivityFindAllResponse::from)
            .toList();
    }

    public ActivityDetailResponse findById(Long activityId) {
        Activity activity = activityRepository.findById(activityId)
            .orElseThrow(() -> new NoSuchElementException("존재하지 않는 활동입니다."));
        return ActivityDetailResponse.from(activity);
    }
}
