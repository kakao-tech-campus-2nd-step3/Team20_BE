package com.gamsa.dataupdate.service;

import com.gamsa.activity.domain.District;
import com.gamsa.activity.domain.Institute;
import com.gamsa.activity.dto.ActivitySaveRequest;
import com.gamsa.activity.dto.InstituteSaveRequest;
import com.gamsa.activity.service.ActivityService;
import com.gamsa.activity.service.DistrictService;
import com.gamsa.activity.service.InstituteService;
import com.gamsa.dataupdate.utils.ActivityDataUtils;
import com.gamsa.dataupdate.utils.KakaoLocalUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ActivityDataUpdateService {
    private final ActivityService activityService;
    private final ActivityDataUtils activityDataUtils;
    private final DistrictService districtService;
    private final InstituteService instituteService;
    private final KakaoLocalUtils kakaoLocalUtils;

    public void update(LocalDate startDate, LocalDate endDate) {
        List<String> programList = activityDataUtils.getVolunteerParticipationList(startDate, endDate);

        log.info("Fetched program list: {}", programList);

        programList.forEach(programNo -> {
            try {
                saveInstitute(programNo);
                saveActivity(programNo);
            } catch (Exception e) {
                log.warn("Failed to process program number {}: {}", programNo, e.getMessage());
            }
        });
    }

    @Transactional
    public void saveInstitute(String programNo) {
        try {
            var instituteApiResponse = activityDataUtils.getInstituteApiResponse(programNo);
            InstituteSaveRequest saveRequest = instituteApiResponse.toSaveRequest(
                    kakaoLocalUtils.getCoordinateByAddress(instituteApiResponse.getLocation()).orElse(
                            districtService.findCoordinates(instituteApiResponse.getSidoGunguCode())));
            instituteService.save(saveRequest);
        } catch (Exception e) {
            log.warn("Failed to save institute for program number {}: {}", programNo, e.getMessage());
            throw e;
        }
    }

    @Transactional
    public void saveActivity(String programNo) {
        try {
            ActivitySaveRequest saveRequest = activityDataUtils.getVolunteerDetail(programNo);

            // Institute 조회 및 검증
            Institute institute = instituteService.findByName(saveRequest.getInstituteName());
            if (institute == null) {
                log.warn("Institute with name {} not found for program number {}", saveRequest.getInstituteName(), programNo);
                throw new IllegalArgumentException("Institute not found: " + saveRequest.getInstituteName());
            }

            // District 조회 및 검증
            District district = districtService.findBySidoGunguCode(saveRequest.getSidoGunguCode());
            if (district == null) {
                log.warn("District with sidoGunguCode {} not found for program number {}", saveRequest.getSidoGunguCode(), programNo);
                throw new IllegalArgumentException("District not found: " + saveRequest.getSidoGunguCode());
            }

            activityService.save(saveRequest, institute, district);

        } catch (Exception e) {
            log.warn("Failed to save activity for program number {}: {}", programNo, e.getMessage());
            throw e;
        }
    }
}