package com.gamsa.activity.repository;

import com.gamsa.activity.dto.ActivityFilterRequest;
import com.gamsa.activity.dto.ActivityFindDistanceOrderRequest;
import com.gamsa.activity.entity.ActivityJpaEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface ActivityCustomRepository {

    Slice<ActivityJpaEntity> findSlice(ActivityFilterRequest request, Pageable pageable);

    Slice<ActivityJpaEntity> findSliceDistanceOrder(
        ActivityFilterRequest filterRequest,
        ActivityFindDistanceOrderRequest distanceOrderRequest,
        Pageable pageable);
}
