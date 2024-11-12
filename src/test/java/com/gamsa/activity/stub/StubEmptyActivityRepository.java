package com.gamsa.activity.stub;

import com.gamsa.activity.domain.Activity;
import com.gamsa.activity.dto.ActivityFilterRequest;
import com.gamsa.activity.dto.ActivityFindDistanceOrderRequest;
import com.gamsa.activity.repository.ActivityRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

public class StubEmptyActivityRepository implements ActivityRepository {

    @Override
    public void save(Activity activity) {
        // do nothing
    }

    @Override
    public Slice<Activity> findSlice(ActivityFilterRequest request, Pageable pageable) {
        return new SliceImpl<>(List.of());
    }

    @Override
    public Slice<Activity> findSliceDistanceOrder(ActivityFilterRequest filterRequest,
        ActivityFindDistanceOrderRequest distanceOrderRequest, Pageable pageable) {
        return new SliceImpl<>(List.of());
    }

    @Override
    public Optional<Activity> findById(Long activityId) {
        return Optional.empty();
    }
}
