package com.gamsa.activity.repository;

import static com.gamsa.activity.entity.QActivityJpaEntity.activityJpaEntity;

import com.gamsa.activity.dto.ActivityFilterRequest;
import com.gamsa.activity.entity.ActivityJpaEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

@RequiredArgsConstructor
public class ActivityCustomRepositoryImpl implements ActivityCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Slice<ActivityJpaEntity> findSlice(ActivityFilterRequest request, Pageable pageable) {
        List<OrderSpecifier> orders = getAllOrderSpecifiers(pageable);
        BooleanBuilder filterBuilder = ActivityFilterBuilder.createFilter(request);

        List<ActivityJpaEntity> results = jpaQueryFactory
            .selectFrom(activityJpaEntity)
            .where(filterBuilder)
            .orderBy(orders.toArray(OrderSpecifier[]::new))
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize() + 1)
            .fetch();

        return checkLastPage(pageable, results);
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

        pageable.getSort().stream()
            .forEach(order -> {
                Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
                String property = order.getProperty();
                PathBuilder orderPath = new PathBuilder(ActivityJpaEntity.class,
                    "activityJpaEntity");
                orders.add(new OrderSpecifier(direction, orderPath.get(property)));
            });
        return orders;
    }
}
