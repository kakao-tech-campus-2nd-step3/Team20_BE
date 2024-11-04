package com.gamsa.activity.service;

import com.gamsa.activity.constant.ActivityErrorCode;
import com.gamsa.activity.domain.District;
import com.gamsa.activity.dto.DistrictFindAllResponse;
import com.gamsa.activity.dto.DistrictSaveRequest;
import com.gamsa.activity.exception.ActivityException;
import com.gamsa.activity.repository.DistrictRepository;

import java.math.BigDecimal;
import java.util.*;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DistrictService {

    private final DistrictRepository districtRepository;

    public void save(DistrictSaveRequest saveRequest) {
        districtRepository.findBySidoGunguCode(saveRequest.getSidoGunguCode())
            .ifPresent(district -> {
                throw new ActivityException(ActivityErrorCode.DISTRICT_ALREADY_EXISTS);
            });
        districtRepository.save(saveRequest.toModel());
    }

    /**
     * 시도 코드 데이터만 반환
     *
     * @return 시도 코드 리스트
     */
    public List<DistrictFindAllResponse> findAllSido() {
        return districtRepository.findAllBysido(true).stream()
            .map(DistrictFindAllResponse::from)
            .toList();
    }

    /**
     * 군구 코드 데이터만 반환
     *
     * @return 군구 코드 리스트
     */
    public List<DistrictFindAllResponse> findAllGungu() {
        return districtRepository.findAllBysido(false).stream()
            .map(DistrictFindAllResponse::from)
            .toList();
    }

    public Map<String, BigDecimal> findCoordinates(int gunguCode) {
        District find = districtRepository.findBySidoGunguCode(gunguCode)
                .orElseThrow(NoSuchElementException::new);
        Map<String, BigDecimal> coordinates = new HashMap<>();
        coordinates.put("longitude", find.getLongitude());
        coordinates.put("latitude", find.getLatitude());
        return coordinates;
    }
}
