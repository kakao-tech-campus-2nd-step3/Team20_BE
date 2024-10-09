package com.gamsa.activity.repository;

import com.gamsa.activity.domain.District;
import com.gamsa.activity.entity.DistrictJpaEntity;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class DistrictRepositoryImpl implements DistrictRepository {

    private final DistrictJpaRepository districtJpaRepository;

    @Override
    public void save(District district) {
        districtJpaRepository.save(DistrictJpaEntity.from(district));
    }

    @Override
    public Optional<District> findByGunguCode(int gunguCode) {
        return districtJpaRepository.findByGunguCode(gunguCode)
            .map(DistrictJpaEntity::toModel);
    }

    @Override
    public List<District> findAllBysido(boolean sido) {
        return districtJpaRepository.findAllBySido(sido).stream()
            .map(DistrictJpaEntity::toModel)
            .toList();
    }
}
