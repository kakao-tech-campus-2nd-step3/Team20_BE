package com.gamsa.avatar.repository;

import com.gamsa.avatar.domain.Avatar;
import java.util.Optional;

public interface AvatarRepository {
    void save(Avatar avatar);

    Optional<Avatar> findById(Long id);

    Optional<Avatar> findByUserId(Long userId);

    Optional<Avatar> findByNickname(String nickname);

    void deleteById(Long id);
}
