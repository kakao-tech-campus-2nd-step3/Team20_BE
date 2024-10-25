package com.gamsa.history.repository;

import com.gamsa.history.entity.HistoryJpaEntity;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.ArrayList;
import java.util.List;

import static com.gamsa.history.entity.QHistoryJpaEntity.historyJpaEntity;

@RequiredArgsConstructor
public class HistoryCustomRepositoryImpl implements HistoryCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Slice<HistoryJpaEntity> findSliceByAvatarId(long avatarId, Pageable pageable) {
        List<OrderSpecifier> orders = getAllOrderSpecifiers(pageable);

        List<HistoryJpaEntity> results = jpaQueryFactory
                .selectFrom(historyJpaEntity)
                .where(historyJpaEntity.avatar.avatarId.eq(avatarId))
                .orderBy(orders.toArray(OrderSpecifier[]::new))
                .offset(pageable.getOffset())
                .limit(pageable.getOffset() + 1)
                .fetch();

        return checkLastPage(pageable, results);
    }

    private Slice<HistoryJpaEntity> checkLastPage(Pageable pageable, List<HistoryJpaEntity> results) {
        boolean hasNest = false;
        if (results.size() > pageable.getPageSize()) {
            hasNest = true;
            results.remove(pageable.getPageSize());
        }
        return new SliceImpl<>(results, pageable, hasNest);
    }

    private List<OrderSpecifier> getAllOrderSpecifiers(Pageable pageable) {
        List<OrderSpecifier> orders = new ArrayList<>();

        pageable.getSort().stream()
                .forEach(order -> {
                            Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
                            String property = order.getProperty();
                            PathBuilder orderPath = new PathBuilder(HistoryJpaEntity.class, "activityJpaEntity");
                            orders.add(new OrderSpecifier(direction, orderPath.get(property)));
                        }
                );
        return orders;
    }
}
