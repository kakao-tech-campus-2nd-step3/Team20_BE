package com.gamsa.activity.service;

import com.gamsa.activity.constant.ActivityErrorCode;
import com.gamsa.activity.domain.Activity;
import com.gamsa.activity.domain.District;
import com.gamsa.activity.domain.Institute;
import com.gamsa.activity.dto.ActivityDetailResponse;
import com.gamsa.activity.dto.ActivityFilterRequest;
import com.gamsa.activity.dto.ActivityFindSliceResponse;
import com.gamsa.activity.dto.ActivitySaveRequest;
import com.gamsa.activity.exception.ActivityException;
import com.gamsa.activity.repository.ActivityRepository;
import com.gamsa.review.domain.Question;
import com.gamsa.review.service.QuestionService;
import com.gamsa.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final QuestionService questionService;
    private final ReviewService reviewService;

    public void save(ActivitySaveRequest saveRequest, Institute institute, District district) {
        activityRepository.save(saveRequest.toModel(institute, district));
    }

    public Slice<ActivityFindSliceResponse> findSlice(ActivityFilterRequest request,
                                                      Pageable pageable) {
        Slice<Activity> activities = activityRepository.findSlice(request, pageable);
        return activities.map(ActivityFindSliceResponse::from);
    }

    public ActivityDetailResponse findById(Long activityId) {
        Activity activity = activityRepository.findById(activityId)
                .orElseThrow(() -> new ActivityException(ActivityErrorCode.ACTIVITY_NOT_EXISTS));


        Map<Question, BigDecimal> scores = new HashMap<>();
        long instituteId = activity.getInstitute().getInstituteId();

        questionService.findAll().forEach(question -> {
            BigDecimal score = reviewService.getAverageScore(instituteId, question.getQuestionId());
            scores.put(question, score);
        });

        return ActivityDetailResponse.from(activity, scores);
    }
}
