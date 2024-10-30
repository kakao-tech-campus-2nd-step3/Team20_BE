package com.gamsa.avatar.stub;

import com.gamsa.avatar.constant.AgeRange;
import com.gamsa.avatar.constant.Experienced;
import com.gamsa.avatar.domain.Avatar;
import com.gamsa.avatar.repository.AvatarRepository;
import com.gamsa.user.entity.UserJpaEntity;
import java.util.Optional;

public class StubExistsAvatarRepository implements AvatarRepository {

    private final UserJpaEntity user = UserJpaEntity.builder()
        .id(1L)
        .nickname("nickname")
        .build();

    private final Avatar avatar = Avatar.builder()
        .avatarId(1L)
        .user(user.toModel())
        .avatarLevel(1L)
        .avatarExp(1L)
        .nickname("닉네임")
        .ageRange(AgeRange.ADULT)
        .experienced(Experienced.NOVICE)
        .build();

    @Override
    public void save(Avatar avatar) {}

    @Override
    public Optional<Avatar> findById(Long id) {
        return Optional.of(avatar);
    }

    @Override
    public Optional<Avatar> findByUserId(Long userId) {
        return Optional.of(avatar);
    }

    @Override
    public Optional<Avatar> findByNickname(String nickname) {
        return Optional.of(avatar);
    }

    @Override
    public void deleteById(Long id) {}
}
