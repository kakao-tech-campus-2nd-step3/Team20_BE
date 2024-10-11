package com.gamsa.activity.repository;

import com.gamsa.activity.domain.Institute;
import com.gamsa.activity.entity.InstituteJpaEntity;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class InstituteRepositoryImpl implements InstituteRepository {

    private final InstituteJpaRepository instituteJpaRepository;

    @Override
    public void save(Institute institute) {
        instituteJpaRepository.save(InstituteJpaEntity.from(institute));
    }

    @Override
    public Optional<Institute> findById(Long id) {
        return instituteJpaRepository.findById(id)
            .map(InstituteJpaEntity::toModel);
    }

    @Override
    public Optional<Institute> findByName(String name) {
        return instituteJpaRepository.findByName(name)
            .map(InstituteJpaEntity::toModel);
    }
}
