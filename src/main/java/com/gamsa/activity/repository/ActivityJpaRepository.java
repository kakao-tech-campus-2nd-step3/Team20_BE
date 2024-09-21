package com.gamsa.activity.repository;

import com.gamsa.activity.entity.ActivityJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityJpaRepository extends JpaRepository<ActivityJpaEntity, Long> {

}
