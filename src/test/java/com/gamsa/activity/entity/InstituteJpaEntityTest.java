package com.gamsa.activity.entity;

import com.gamsa.activity.domain.District;
import com.gamsa.activity.domain.Institute;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class InstituteJpaEntityTest {


    @Test
    @DisplayName("도메인 모델에서 JPA엔티티로 변환")
    void changeToJpaEntity() {
        // given
        District district = District.builder()
                .sidoCode(1234)
                .sidoGunguCode(8888)
                .sidoName("서울특별시")
                .gunguName("강남구")
                .sido(false)
                .build();

        Institute model = Institute.builder()
                .instituteId(1L)
                .name("도서관")
                .location("서울시")
                .latitude(new BigDecimal("37.56100278"))
                .longitude(new BigDecimal("126.9996417"))
                .sidoGungu(district)
                .phone("010xxxxxxxx")
                .build();
        // when
        InstituteJpaEntity jpaEntity = InstituteJpaEntity.from(model);
        // then
        assertThat(jpaEntity.getInstituteId()).isEqualTo(model.getInstituteId());
    }

    @Test
    @DisplayName("JPA엔티티에서 도메인 모델로 변환")
    void changeToDomainModel() {
        // given
        DistrictJpaEntity districtJpaEntity = DistrictJpaEntity.builder()
                .sidoCode(1234)
                .sidoGunguCode(8888)
                .sidoName("서울특별시")
                .gunguName("강남구")
                .sido(false)
                .build();

        InstituteJpaEntity jpaEntity = InstituteJpaEntity.builder()
                .instituteId(1L)
                .name("도서관")
                .location("서울시")
                .latitude(new BigDecimal("37.56100278"))
                .longitude(new BigDecimal("126.9996417"))
                .sidoGungu(districtJpaEntity)
                .phone("010xxxxxxxx")
                .build();
        // when
        Institute model = jpaEntity.toModel();
        // then
        assertThat(model.getInstituteId()).isEqualTo(jpaEntity.getInstituteId());
    }

}