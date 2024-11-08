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
import com.gamsa.activity.repository.DistrictRepository;
import com.gamsa.activity.repository.InstituteRepository;
import com.gamsa.review.dto.QuestionResponse;
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
    private final InstituteRepository instituteRepository;
    private final DistrictRepository districtRepository;
    private final QuestionService questionService;
    private final ReviewService reviewService;

    public void save(ActivitySaveRequest saveRequest) {
        // 중복 여부 확인
        activityRepository.findById(saveRequest.getActId())
                .ifPresent(activity -> {
                    throw new ActivityException(ActivityErrorCode.ACTIVITY_ALREADY_EXISTS);
                });
        // 기관 존재 확인
        Institute institute = instituteRepository.findById(saveRequest.getInstituteId())
                .orElseThrow(() -> new ActivityException(ActivityErrorCode.INSTITUTE_NOT_EXISTS));

        // 시도, 군구 존재 확인
        District sidoGungu = districtRepository.findBySidoGunguCode(saveRequest.getSidoGunguCode())
                .orElseThrow(() -> new ActivityException(ActivityErrorCode.DISTRICT_NOT_EXISTS));

        activityRepository.save(saveRequest.toModel(institute, sidoGungu));
    }

    public Slice<ActivityFindSliceResponse> findSlice(ActivityFilterRequest request,
                                                      Pageable pageable) {
        Slice<Activity> activities = activityRepository.findSlice(request, pageable);
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
