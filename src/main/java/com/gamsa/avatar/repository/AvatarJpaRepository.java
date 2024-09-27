package com.gamsa.avatar.repository;

import com.gamsa.avatar.domain.Avatar;
import com.gamsa.avatar.entity.AvatarJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AvatarJpaRepository extends JpaRepository<AvatarJpaEntity, Long> {
    AvatarJpaEntity save(AvatarJpaEntity jpaEntity);
    void deleteById(Long id);
    Optional<AvatarJpaEntity> findById(Long id);
}
