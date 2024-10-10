package com.gamsa.activity.stub;

import com.gamsa.activity.domain.District;
import com.gamsa.activity.repository.DistrictRepository;
import java.util.List;
import java.util.Optional;

public class StubExistsDistrictRepository implements DistrictRepository {

    private final District district = District.builder()
        .sidoCode(1234)
        .sidoGunguCode(8888)
        .sidoName("서울특별시")
        .gunguName("강남구")
        .sido(false)
        .build();

    @Override
    public void save(District district) {
        // do nothing
    }

    @Override
    public Optional<District> findBySidoGunguCode(int gunguCode) {
        return Optional.of(district);
    }

    @Override
    public List<District> findAllBysido(boolean sido) {
        return List.of(district);
    }
}
