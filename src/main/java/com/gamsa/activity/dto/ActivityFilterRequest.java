package com.gamsa.activity.dto;

import com.gamsa.activity.constant.Category;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ActivityFilterRequest {

    // 카테고리
    private final Category category;

    // Todo 지역

    // 청소년 가능한 것만
    private final boolean teenPossibleOnly;

    // 마감된 활동만
    private final boolean deadlineEndOnly;
}
