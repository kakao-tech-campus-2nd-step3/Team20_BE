package com.gamsa.user.stub;

import com.gamsa.user.repository.KakaoAccessTokenRepository;
import java.util.Optional;

public class DummyKakaoAccessTokenRepository extends KakaoAccessTokenRepository {

    @Override
    public Optional<String> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Long id, String token) {
        // do nothing
    }
}
