package com.gamsa.activity.service;

import com.gamsa.activity.constant.ActivityErrorCode;
import com.gamsa.activity.domain.Activity;
import com.gamsa.activity.domain.District;
import com.gamsa.activity.domain.Institute;
import com.gamsa.activity.dto.ActivityDetailResponse;
import com.gamsa.activity.dto.ActivityFilterRequest;
import com.gamsa.activity.dto.ActivityFindDistanceOrderRequest;
import com.gamsa.activity.dto.ActivityFindSliceResponse;
import com.gamsa.activity.dto.ActivitySaveRequest;
import com.gamsa.activity.exception.ActivityException;
import com.gamsa.activity.repository.ActivityRepository;
import com.gamsa.review.dto.QuestionResponse;
import com.gamsa.review.service.QuestionService;
import com.gamsa.review.service.ReviewService;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final QuestionService questionService;
    private final ReviewService reviewService;

    public void save(ActivitySaveRequest saveRequest, Institute institute, District district) {
        activityRepository.save(saveRequest.toModel(institute, district));
    }

    public Slice<ActivityFindSliceResponse> findSlice(ActivityFilterRequest filterRequest,
        ActivityFindDistanceOrderRequest distanceOrderRequest,
        Pageable pageable) {

        Slice<Activity> activities;
        // 가까운순 정렬
        if (pageable.getSort().toString().contains("distance")) {
            activities = activityRepository
                .findSliceDistanceOrder(filterRequest, distanceOrderRequest, pageable);
        } else {
            activities = activityRepository.findSlice(filterRequest, pageable);
        }

        return activities.map(ActivityFindSliceResponse::from);
    }

    public ActivityDetailResponse findById(Long activityId) {
        Activity activity = activityRepository.findById(activityId)
                .orElseThrow(() -> new ActivityException(ActivityErrorCode.ACTIVITY_NOT_EXISTS));


        Map<QuestionResponse, BigDecimal> scores = new HashMap<>();
        long instituteId = activity.getInstitute().getInstituteId();

        questionService.findAllResponse().forEach(question -> {
            BigDecimal score = reviewService.getAverageScore(instituteId, question.getQuestionId());
            scores.put(question, score);
        });

        return ActivityDetailResponse.from(activity, scores);
    }
}
