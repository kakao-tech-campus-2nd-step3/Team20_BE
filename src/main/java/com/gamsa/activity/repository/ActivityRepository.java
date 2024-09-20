package com.gamsa.activity.repository;

import com.gamsa.activity.domain.Activity;
import java.util.List;
import java.util.Optional;

public interface ActivityRepository {

    void save(Activity activity);

    List<Activity> findAll();

    Optional<Activity> findById(Long activityId);
}
