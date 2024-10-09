package com.gamsa.activity.repository;

import com.gamsa.activity.domain.District;
import java.util.List;
import java.util.Optional;

public interface DistrictRepository {

    void save(District district);

    Optional<District> findByGunguCode(int gunguCode);

    List<District> findAllBysido(boolean sido);
}
