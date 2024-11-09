package com.gamsa.activity.service;

import com.gamsa.activity.domain.District;
import com.gamsa.activity.dto.DistrictFindAllResponse;
import com.gamsa.activity.dto.DistrictSaveRequest;
import com.gamsa.activity.repository.DistrictRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class DistrictService {

    private final DistrictRepository districtRepository;

    public void save(DistrictSaveRequest saveRequest) {
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
        if (gunguCode == 0) {
            Map<String, BigDecimal> defaultCoordinates = new HashMap<>();
            defaultCoordinates.put("longitude", null); // 기본값으로 null 저장
            defaultCoordinates.put("latitude", null);
            return defaultCoordinates;
        }

        return districtRepository.findBySidoGunguCode(gunguCode)
                .map(district -> {
                    Map<String, BigDecimal> coordinates = new HashMap<>();
                    coordinates.put("longitude", district.getLongitude());
                    coordinates.put("latitude", district.getLatitude());
                    return coordinates;
                })
                .orElseThrow(() -> new NoSuchElementException("District not found for gunguCode: " + gunguCode));
    }

    public District findBySidoGunguCode(int gunguCode) {
        return districtRepository.findBySidoGunguCode(gunguCode)
                .orElseThrow(() -> new NoSuchElementException("District not found for gunguCode: " + gunguCode));
    }
}
