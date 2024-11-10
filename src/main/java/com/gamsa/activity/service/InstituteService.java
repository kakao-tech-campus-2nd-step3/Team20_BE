package com.gamsa.activity.service;

import com.gamsa.activity.constant.ActivityErrorCode;
import com.gamsa.activity.domain.District;
import com.gamsa.activity.domain.Institute;
import com.gamsa.activity.dto.InstituteSaveRequest;
import com.gamsa.activity.exception.ActivityException;
import com.gamsa.activity.repository.DistrictRepository;
import com.gamsa.activity.repository.InstituteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class InstituteService {

    private final InstituteRepository instituteRepository;
    private final DistrictRepository districtRepository;

    public void save(InstituteSaveRequest saveRequest) {
        instituteRepository.findByName(saveRequest.getName())
                .ifPresentOrElse(
                        institute -> log.warn("Institute already exists: {}", saveRequest.getName()),
                        () -> {
                            District district = districtRepository.findBySidoGunguCode(saveRequest.getSidoGunguCode())
                                    .orElseThrow(() -> new ActivityException(ActivityErrorCode.DISTRICT_NOT_EXISTS));

                            log.info("Saving new institute for district: {}", district);
                            instituteRepository.save(saveRequest.toModel(district));
                        }
                );
    }

    public Institute findByName(String name) {
        log.info("Searching for institute by name: {}", name);
        return instituteRepository.findByName(name)
                .orElseThrow(() -> new ActivityException(ActivityErrorCode.INSTITUTE_NOT_EXISTS));
    }
}
