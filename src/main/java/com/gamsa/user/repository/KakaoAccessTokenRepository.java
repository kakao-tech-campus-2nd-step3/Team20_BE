package com.gamsa.user.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class KakaoAccessTokenRepository {

    private final Map<Long, String> tokenRepository = new HashMap<>();

    public Optional<String> findById(Long id) {
        return Optional.of(tokenRepository.getOrDefault(id, null));
    }

    public void save(Long id, String token) {
        tokenRepository.put(id, token);
    }
}
