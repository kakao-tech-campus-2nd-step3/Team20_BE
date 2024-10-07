package com.gamsa.activity.repository;

import com.gamsa.activity.domain.Activity;
import com.gamsa.activity.dto.ActivityFilterRequest;
import com.gamsa.activity.entity.ActivityJpaEntity;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
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
    public Slice<Activity> findSlice(ActivityFilterRequest request, Pageable pageable) {
        return activityJpaRepository.findSlice(request, pageable)
            .map(ActivityJpaEntity::toModel);
    }

    @Override
    public Optional<Activity> findById(Long activityId) {
        return activityJpaRepository.findById(activityId)
            .map(ActivityJpaEntity::toModel);
    }
}
