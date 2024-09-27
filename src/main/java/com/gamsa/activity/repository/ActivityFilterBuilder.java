package com.gamsa.activity.repository;

import static com.gamsa.activity.entity.QActivityJpaEntity.activityJpaEntity;

import com.gamsa.activity.constant.Category;
import com.gamsa.activity.dto.ActivityFilterRequest;
import com.querydsl.core.BooleanBuilder;
import java.time.LocalDateTime;

public class ActivityFilterBuilder {

    public static BooleanBuilder createFilter(ActivityFilterRequest request) {
        BooleanBuilder filterBuilder = new BooleanBuilder();

        eqCategory(filterBuilder, request.getCategory());
        isTeenPossibleOnly(filterBuilder, request.isTeenPossibleOnly());
        isDeadlineEndOnly(filterBuilder, request.isBeforeDeadlineOnly());

        return filterBuilder;
    }

    public static void eqCategory(BooleanBuilder filterBuilder, Category category) {
        if (category != null) {
            filterBuilder.and(activityJpaEntity.category.eq(category));
        }
    }

    public static void isTeenPossibleOnly(BooleanBuilder filterBuilder, boolean teenPossibleOnly) {
        if (teenPossibleOnly) {
            filterBuilder.and(activityJpaEntity.teenPossible.isTrue());
        }
    }

    public static void isDeadlineEndOnly(BooleanBuilder filterBuilder, boolean beforeDeadlineOnly) {
        if (beforeDeadlineOnly) {
            filterBuilder.and(activityJpaEntity.noticeEndDate.after(LocalDateTime.now()));
        }
    }
}
