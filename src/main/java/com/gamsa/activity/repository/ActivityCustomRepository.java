package com.gamsa.activity.repository;

import com.gamsa.activity.entity.ActivityJpaEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface ActivityCustomRepository {

    Slice<ActivityJpaEntity> findSlice(Pageable pageable);

}
