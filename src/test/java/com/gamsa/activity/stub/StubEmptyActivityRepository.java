package com.gamsa.activity.stub;

import com.gamsa.activity.domain.Activity;
import com.gamsa.activity.repository.ActivityRepository;
import java.util.List;
import java.util.Optional;

public class StubEmptyActivityRepository implements ActivityRepository {

    @Override
    public void save(Activity activity) {
        // do nothing
    }

    @Override
    public List<Activity> findAll() {
        return List.of();
    }

    @Override
    public Optional<Activity> findById(Long activityId) {
        return Optional.empty();
    }
}
