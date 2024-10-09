package com.gamsa.activity.entity;

import com.gamsa.activity.domain.District;
import com.gamsa.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "District")
public class DistrictJpaEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "district_id")
    private Integer districtId;

    @Column(name = "sido_code")
    private int sidoCode;

    @Column(name = "gungu_code")
    private int gunguCode;

    @Column(name = "sido_name", length = 15)
    private String sidoName;

    @Column(name = "gungu_name", length = 15)
    private String gunguName;

    @Column(name = "sido")
    private boolean sido;

    public static DistrictJpaEntity from(District district) {
        return DistrictJpaEntity.builder()
            .sidoCode(district.getSidoCode())
            .gunguCode(district.getGunguCode())
            .sidoName(district.getSidoName())
            .gunguName(district.getGunguName())
            .sido(district.isSido())
            .build();
    }

    public District toModel() {
        return District.builder()
            .sidoCode(getSidoCode())
            .gunguCode(getGunguCode())
            .sidoName(getSidoName())
            .gunguName(getGunguName())
            .sido(isSido())
            .build();
    }
}
