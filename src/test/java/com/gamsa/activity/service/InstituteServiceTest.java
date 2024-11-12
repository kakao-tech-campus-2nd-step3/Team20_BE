package com.gamsa.activity.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.gamsa.activity.dto.InstituteSaveRequest;
import com.gamsa.activity.stub.StubEmptyInstituteRepository;
import com.gamsa.activity.stub.StubExistsDistrictRepository;
import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InstituteServiceTest {

    private final InstituteSaveRequest saveRequest = InstituteSaveRequest.builder()
        .name("도서관")
        .location("서울시")
        .latitude(new BigDecimal("123456789.12341234"))
        .longitude(new BigDecimal("987654321.43214321"))
        .phone("010xxxxxxxx")
        .build();


    @Test
    @DisplayName("봉사기관을 생성한다.")
    void save() {
        // given
        InstituteService service = new InstituteService(
            new StubEmptyInstituteRepository(),
            new StubExistsDistrictRepository()
        );
        // then
        assertDoesNotThrow(() -> {
            // when
            service.save(saveRequest);
        });
    }
}