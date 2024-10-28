package com.gamsa.user.repository;

import com.gamsa.user.entity.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserJpaEntity, Long> {

}
