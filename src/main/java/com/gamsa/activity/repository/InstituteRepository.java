package com.gamsa.activity.repository;

import com.gamsa.activity.domain.Institute;
import java.util.Optional;

public interface InstituteRepository {

    void save(Institute institute);

    Optional<Institute> findById(Long id);

    Optional<Institute> findByName(String name);
}
