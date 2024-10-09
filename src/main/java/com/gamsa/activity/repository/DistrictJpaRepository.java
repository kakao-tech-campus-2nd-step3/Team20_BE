package com.gamsa.activity.repository;

import com.gamsa.activity.entity.DistrictJpaEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistrictJpaRepository extends JpaRepository<DistrictJpaEntity, Integer> {

    Optional<DistrictJpaEntity> findByGunguCode(int gunguCode);

    List<DistrictJpaEntity> findAllBySido(boolean sido);
}
