package com.gamsa.dataupdate;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DataUpdateErrorCode {
    // 1365 API 오류
    OPENAPI_NOT_RESPOND(504, "Open API가 응답하지 않습니다."),
    OPENAPI_ERROR(504, "Open API의 반환 값을 처리할 수 없습니다."),


    // 카카오 API 오류
    KAKAOLOCALAPI_NOT_RESPOND(504, "카카오 API가 정상적으로 응답하지 않습니다."),
    KAKAOLOCALAPT_ERROR(504, "카카오 API의 반환 값을 처리할 수 없습니다."),

    // 내부 처리 오류
    INVALID_CSV(500, "주어진 CSV 파일을 처리할 수 없습니다"),
    INVALID_FILE_SOURCE(500, "주어진 파일 경로가 올바르지 않습니다.");


    private final int ststus;
    private final String msg;
}
