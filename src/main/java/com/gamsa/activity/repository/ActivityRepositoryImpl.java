package com.gamsa.activity.repository;

import com.gamsa.activity.domain.Activity;
import com.gamsa.activity.entity.ActivityJpaEntity;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ActivityRepositoryImpl implements ActivityRepository {

    private final ActivityJpaRepository activityJpaRepository;

    @Override
    public void save(Activity activity) {
        activityJpaRepository.save(ActivityJpaEntity.from(activity));
    }

    @Override
    public List<Activity> findAll() {
        return activityJpaRepository.findAll()
            .stream().map(ActivityJpaEntity::toModel)
            .toList();
    }

    @Override
    public Optional<Activity> findById(Long activityId) {
        return activityJpaRepository.findById(activityId)
            .map(ActivityJpaEntity::toModel);
    }
}
