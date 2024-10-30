package com.gamsa.activtydata;

import com.gamsa.activity.dto.ActivitySaveRequest;
import com.gamsa.activity.dto.InstituteSaveRequest;
import com.gamsa.activity.service.ActivityService;
import com.gamsa.activity.service.DistrictService;
import com.gamsa.activity.service.InstituteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ActivityDataUpdateService {
    private final ActivityService activityService;
    private final ActivityDataUtils activityDataUtils;
    private final KakaoLocalUtils kakaoLocalUtils;

    DistrictService districtService;
    InstituteService instituteService;

    public void update() {
        String today = LocalDate.now().toString();
        String yesterday = LocalDate.now().minusDays(1).toString();

        List<String> programList = activityDataUtils.getVolunteerParticipationList(today, yesterday);

        List<InstituteSaveRequest> saveRequests = programList.stream()
                .map(activityDataUtils::getInstituteApiResponse)
                .map(instituteApiResponse -> {
                    return instituteApiResponse.toSaveRequest(kakaoLocalUtils.getCoordinateByKeyword(instituteApiResponse.getLocation())
                            .orElse(districtService.findCoordinates(instituteApiResponse.getSidoGunguCode())));
                })
                .toList();

        saveRequests.forEach(instituteService::save);

        List<ActivitySaveRequest> activitySaveRequests = programList.stream()
                .map(activityDataUtils::getVolunteerDetail)
                .map(activityApiResponse -> {
                    return activityApiResponse.toSaveRequest(instituteService.findByName(activityApiResponse.getInstituteName()));
                })
                .toList();

        activitySaveRequests.forEach(activityService::save);

    }
}