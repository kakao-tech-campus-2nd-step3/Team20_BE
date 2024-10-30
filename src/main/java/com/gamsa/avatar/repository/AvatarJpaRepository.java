package com.gamsa.avatar.repository;

import com.gamsa.avatar.entity.AvatarJpaEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvatarJpaRepository extends JpaRepository<AvatarJpaEntity, Long> {

    Optional<AvatarJpaEntity> findByUserId(Long userId);

    Optional<AvatarJpaEntity> findByNickname(String nickname);
}
