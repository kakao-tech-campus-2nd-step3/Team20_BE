package com.gamsa.avatar.stub;

import com.gamsa.avatar.constant.AgeRange;
import com.gamsa.avatar.constant.Experienced;
import com.gamsa.avatar.domain.Avatar;
import com.gamsa.avatar.repository.AvatarRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public class StubAvatarRepository implements AvatarRepository {
    private final Avatar avatar = Avatar.builder()
            .avatarId(1L)
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
    public void deleteById(Long id) {}
}
