package com.gamsa.avatar.repository;

import com.gamsa.avatar.domain.Avatar;
import com.gamsa.avatar.entity.AvatarJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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
        return avatarJpaRepository.findById(id).map(AvatarJpaEntity::toAvatar);
    }

    @Override
    public void deleteById(Long id) {
        avatarJpaRepository.deleteById(id);
    }
}
