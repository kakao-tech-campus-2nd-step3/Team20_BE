package com.gamsa.activity.service;

import com.gamsa.activity.constant.ActivityErrorCode;
import com.gamsa.activity.dto.InstituteSaveRequest;
import com.gamsa.activity.exception.ActivityException;
import com.gamsa.activity.repository.InstituteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class InstituteService {

    private final InstituteRepository instituteRepository;

    public void save(InstituteSaveRequest saveRequest) {
        instituteRepository.findByName(saveRequest.getName())
            .ifPresent(institute -> {
                throw new ActivityException(ActivityErrorCode.INSTITUTE_ALREADY_EXISTS);
            });
        instituteRepository.save(saveRequest.toModel());
    }
}
