package com.gamsa.user.stub;

import com.gamsa.user.domain.User;
import com.gamsa.user.repository.UserRepository;
import java.util.Optional;

public class StubExistsUserRepository implements UserRepository {

    private final User user = User.builder()
        .id(1L)
        .nickname("nickname")
        .build();

    @Override
    public void save(User user) {
        // do nothing
    }

    @Override
    public Optional<User> findById(Long userId) {
        return Optional.of(user);
    }
}
