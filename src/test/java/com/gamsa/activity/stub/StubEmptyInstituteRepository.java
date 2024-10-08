package com.gamsa.activity.stub;

import com.gamsa.activity.domain.Institute;
import com.gamsa.activity.repository.InstituteRepository;
import java.util.Optional;

public class StubEmptyInstituteRepository implements InstituteRepository {

    @Override
    public void save(Institute institute) {
        // do nothing
    }

    @Override
    public Optional<Institute> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Institute> findByName(String name) {
        return Optional.empty();
    }
}
