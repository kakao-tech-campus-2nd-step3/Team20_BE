package com.gamsa.activity.dto;

import com.gamsa.activity.constant.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class ActivityFilterRequest {

    // 카테고리
    private final Category category;

    // 시도군구 코드
    private final Integer sidoGunguCode;

    // 시도 코드
    private final Integer sidoCode;

    // 청소년 가능한 것만
    private final boolean teenPossibleOnly;

    // 마감되지 않은 활동만
    private final boolean beforeDeadlineOnly;

    // 키워드 검색
    private final String keyword;
}
