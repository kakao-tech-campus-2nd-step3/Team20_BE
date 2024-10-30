package com.gamsa.avatar.repository;

import com.gamsa.avatar.domain.Avatar;
import com.gamsa.avatar.entity.AvatarJpaEntity;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class AvatarRepositoryImpl implements AvatarRepository {
    private final AvatarJpaRepository avatarJpaRepository;

    @Override
    public void save(Avatar avatar) {
        avatarJpaRepository.save(AvatarJpaEntity.from(avatar));
    }

    @Override
    public Optional<Avatar> findById(Long id) {
        return avatarJpaRepository.findById(id).map(AvatarJpaEntity::toModel);
    }

    @Override
    public Optional<Avatar> findByUserId(Long userId) {
        return avatarJpaRepository.findByUserId(userId)
            .map(AvatarJpaEntity::toModel);
    }

    @Override
    public Optional<Avatar> findByNickname(String nickname) {
        return avatarJpaRepository.findByNickname(nickname)
            .map(AvatarJpaEntity::toModel);
    }

    @Override
    public void deleteById(Long id) {
        avatarJpaRepository.deleteById(id);
    }
}
