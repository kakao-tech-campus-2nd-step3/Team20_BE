package com.gamsa.activity.repository;

import static com.gamsa.activity.entity.QActivityJpaEntity.activityJpaEntity;
import static org.springframework.util.ObjectUtils.isEmpty;

import com.gamsa.activity.constant.ActivitySortType;
import com.gamsa.activity.domain.Activity;
import com.gamsa.activity.entity.ActivityJpaEntity;
import com.gamsa.common.utils.QueryDslUtil;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.domain.Sort;

@RequiredArgsConstructor
public class ActivityCustomRepositoryImpl implements ActivityCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Slice<Activity> findSlice(Pageable pageable) {
        List<OrderSpecifier> orders = getAllOrderSpecifiers(pageable);

        List<ActivityJpaEntity> results = jpaQueryFactory
            .selectFrom(activityJpaEntity)
            .orderBy(orders.toArray(OrderSpecifier[]::new))
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize() + 1)
            .fetch();

        return checkLastPage(pageable, results)
            .map(ActivityJpaEntity::toModel);
    }

    // 무한스크롤 처리
    private Slice<ActivityJpaEntity> checkLastPage(Pageable pageable,
        List<ActivityJpaEntity> results) {

        boolean hasNext = false;
        if (results.size() > pageable.getPageSize()) {
            hasNext = true;
            results.remove(pageable.getPageSize());
        }
        return new SliceImpl<>(results, pageable, hasNext);
    }

    // 정렬 기준에 맞는 OrderSpecifier 생성
    private List<OrderSpecifier> getAllOrderSpecifiers(Pageable pageable) {
        List<OrderSpecifier> orders = new ArrayList<>();

        if (!isEmpty(pageable.getSort())) {
            for (Sort.Order order : pageable.getSort()) {
                Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
                switch (order.getProperty()) {
                    case ActivitySortType.ID:
                        OrderSpecifier<?> orderId = QueryDslUtil.getSortedColumn(direction,
                            activityJpaEntity, ActivitySortType.ID);
                        orders.add(orderId);
                        break;
                    default:
                        break;
                }
            }
        }
        return orders;
    }
}
