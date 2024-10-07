package com.gamsa.activity.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.gamsa.activity.entity.InstituteJpaEntity;
import com.gamsa.common.config.TestConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(TestConfig.class)
class InstituteJpaRepositoryTest {

    @Autowired
    private InstituteJpaRepository instituteJpaRepository;

    private final InstituteJpaEntity jpaEntity = InstituteJpaEntity.builder()
        .instituteId(1L)
        .name("도서관")
        .location("서울시")
        .latitude(123456789L)
        .longitude(987654321L)
        .phone("010xxxxxxxx")
        .build();

    @Test
    @DisplayName("새 봉사기관 저장")
    void save() {
        // when
        instituteJpaRepository.save(jpaEntity);
        // then
        assertThat(instituteJpaRepository.findById(1L).get().getName())
            .isEqualTo(jpaEntity.getName());
    }
}