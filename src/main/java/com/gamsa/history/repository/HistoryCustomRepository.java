package com.gamsa.history.repository;

import com.gamsa.history.entity.HistoryJpaEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface HistoryCustomRepository {
    Slice<HistoryJpaEntity> findSliceByAvatarId(long avatarId, Pageable pageable);
}
