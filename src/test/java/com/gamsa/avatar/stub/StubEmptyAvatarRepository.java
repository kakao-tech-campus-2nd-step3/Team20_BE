package com.gamsa.avatar.stub;

import com.gamsa.avatar.domain.Avatar;
import com.gamsa.avatar.repository.AvatarRepository;
import java.util.Optional;

public class StubEmptyAvatarRepository implements AvatarRepository {

    @Override
    public void save(Avatar avatar) {
        // do nothing
    }

    @Override
    public Optional<Avatar> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Avatar> findByUserId(Long userId) {
        return Optional.empty();
    }

    @Override
    public Optional<Avatar> findByNickname(String nickname) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        // do nothing
    }
}
