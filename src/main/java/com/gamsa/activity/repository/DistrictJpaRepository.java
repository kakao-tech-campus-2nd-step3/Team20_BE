package com.gamsa.activity.repository;

import com.gamsa.activity.entity.DistrictJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DistrictJpaRepository extends JpaRepository<DistrictJpaEntity, Integer> {

    Optional<DistrictJpaEntity> findBySidoGunguCode(int gunguCode);

    List<DistrictJpaEntity> findAllBySido(boolean sido);
}
