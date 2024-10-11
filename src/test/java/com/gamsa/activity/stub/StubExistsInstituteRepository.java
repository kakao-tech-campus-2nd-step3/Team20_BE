package com.gamsa.activity.stub;

import com.gamsa.activity.domain.Institute;
import com.gamsa.activity.repository.InstituteRepository;
import java.math.BigDecimal;
import java.util.Optional;

public class StubExistsInstituteRepository implements InstituteRepository {

    private final Institute institute = Institute.builder()
        .instituteId(1L)
        .name("도서관")
        .location("서울시")
        .latitude(new BigDecimal("123456789.12341234"))
        .longitude(new BigDecimal("987654321.43214321"))
        .phone("010xxxxxxxx")
        .build();

    @Override
    public void save(Institute institute) {
        // do nothing
    }

    @Override
    public Optional<Institute> findById(Long id) {
        return Optional.of(institute);
    }

    @Override
    public Optional<Institute> findByName(String name) {
        return Optional.of(institute);
    }
}
