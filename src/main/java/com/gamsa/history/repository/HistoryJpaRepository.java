package com.gamsa.history.repository;

import com.gamsa.history.entity.HistoryJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryJpaRepository extends JpaRepository<HistoryJpaEntity, Long>,
        HistoryCustomRepository {
}
