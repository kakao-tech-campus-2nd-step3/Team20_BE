package com.gamsa.activity.repository;

import com.gamsa.activity.entity.InstituteJpaEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstituteJpaRepository extends JpaRepository<InstituteJpaEntity, Long> {

    Optional<InstituteJpaEntity> findByName(String name);
}
