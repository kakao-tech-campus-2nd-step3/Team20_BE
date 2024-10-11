package com.gamsa.activity.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.gamsa.activity.constant.ActivityErrorCode;
import com.gamsa.activity.dto.InstituteSaveRequest;
import com.gamsa.activity.exception.ActivityException;
import com.gamsa.activity.stub.StubEmptyInstituteRepository;
import com.gamsa.activity.stub.StubExistsDistrictRepository;
import com.gamsa.activity.stub.StubExistsInstituteRepository;
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

    @Test
    @DisplayName("봉시기관 동일 이름 충돌로 실패한다.")
    void saveFail() {
        // given
        InstituteService service = new InstituteService(
            new StubExistsInstituteRepository(),
            new StubExistsDistrictRepository()
        );
        // then
        assertThrows(ActivityException.class, () -> {
            // when
            service.save(saveRequest);
        }, ActivityErrorCode.INSTITUTE_ALREADY_EXISTS.getMsg());
    }
}