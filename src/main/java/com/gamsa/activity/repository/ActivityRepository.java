package com.gamsa.activity.repository;

import com.gamsa.activity.domain.Activity;
import com.gamsa.activity.dto.ActivityFilterRequest;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface ActivityRepository {

    void save(Activity activity);

    Slice<Activity> findSlice(ActivityFilterRequest request, Pageable pageable);

    Optional<Activity> findById(Long activityId);
}
