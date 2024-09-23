package com.gamsa.activity.service;

import com.gamsa.activity.domain.Activity;
import com.gamsa.activity.dto.ActivityDetailResponse;
import com.gamsa.activity.dto.ActivityFindAllResponse;
import com.gamsa.activity.dto.ActivitySaveRequest;
import com.gamsa.activity.exception.ActivityAlreadyExistsException;
import com.gamsa.activity.exception.ExceptionMessage;
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
        activityRepository.findById(saveRequest.getActId())
            .ifPresent(activity -> {
                throw new ActivityAlreadyExistsException(
                    ExceptionMessage.ACTIVITY_ALREADY_EXISTS.getMsg());
            });
        activityRepository.save(saveRequest.toModel());
    }

    public List<ActivityFindAllResponse> findAll() {
        List<Activity> activities = activityRepository.findAll();
        return activities.stream()
            .map(ActivityFindAllResponse::from)
            .toList();
    }

    public ActivityDetailResponse findById(Long activityId) {
        Activity activity = activityRepository.findById(activityId)
            .orElseThrow(
                () -> new NoSuchElementException(ExceptionMessage.ACTIVITY_NOT_EXISTS.getMsg()));
        return ActivityDetailResponse.from(activity);
    }
}
