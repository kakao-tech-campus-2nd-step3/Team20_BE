package com.gamsa.activity.service;

import com.gamsa.activity.constant.ActivityErrorCode;
import com.gamsa.activity.domain.District;
import com.gamsa.activity.dto.InstituteSaveRequest;
import com.gamsa.activity.exception.ActivityException;
import com.gamsa.activity.repository.DistrictRepository;
import com.gamsa.activity.repository.InstituteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class InstituteService {

    private final InstituteRepository instituteRepository;
    private final DistrictRepository districtRepository;

    public void save(InstituteSaveRequest saveRequest) {
        instituteRepository.findByName(saveRequest.getName())
            .ifPresent(institute -> {
                throw new ActivityException(ActivityErrorCode.INSTITUTE_ALREADY_EXISTS);
            });

        District district = districtRepository.findBySidoGunguCode(saveRequest.getSidoGunguCode())
            .orElseThrow(() -> new ActivityException(ActivityErrorCode.DISTRICT_NOT_EXISTS));

        instituteRepository.save(saveRequest.toModel(district));
    }

    public Long findByName(String name) {
        return instituteRepository.findByName(name).orElseThrow(() -> new ActivityException(ActivityErrorCode.INSTITUTE_NOT_EXISTS)).getInstituteId();
    }
}
