package com.gamsa.activity.entity;

import com.gamsa.activity.domain.Institute;
import com.gamsa.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "Institute")
public class InstituteJpaEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "institute_id")
    private Long instituteId;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "location", length = 255)
    private String location;

    @Column(name = "latitude")
    private BigDecimal latitude;

    @Column(name = "longitude")
    private BigDecimal longitude;

    // Todo 시군구 코드

    @Column(name = "phone", length = 12)
    private String phone;

    public static InstituteJpaEntity from(Institute institute) {
        return InstituteJpaEntity.builder()
            .instituteId(institute.getInstituteId())
            .name(institute.getName())
            .location(institute.getLocation())
            .latitude(institute.getLatitude())
            .longitude(institute.getLongitude())
            .phone(institute.getPhone())
            .build();
    }

    public Institute toModel() {
        return Institute.builder()
            .instituteId(instituteId)
            .name(name)
            .location(location)
            .latitude(latitude)
            .longitude(longitude)
            .phone(phone)
            .build();
    }
}
