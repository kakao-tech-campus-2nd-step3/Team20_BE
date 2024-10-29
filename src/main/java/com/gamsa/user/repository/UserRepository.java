package com.gamsa.user.repository;

import com.gamsa.user.domain.User;
import java.util.Optional;

public interface UserRepository {

    void save(User user);

    Optional<User> findById(Long userId);

}
