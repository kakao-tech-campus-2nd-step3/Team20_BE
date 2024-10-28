package com.gamsa.history.repository;

import com.gamsa.history.domain.History;
import com.gamsa.history.entity.HistoryJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class HistoryRepositoryImpl implements HistoryRepository {
    private final HistoryJpaRepository historyJpaRepository;

    @Override
    public void save(History history) {
        historyJpaRepository.save(HistoryJpaEntity.from(history));
    }

    @Override
    public Slice<History> findSliceByAvatarId(long avatarId, Pageable pageable) {
        return historyJpaRepository.findSliceByAvatarId(avatarId, pageable)
                .map(entity -> entity.toModel());
    }

    @Override
    public void delete(History history) {
        historyJpaRepository.delete(HistoryJpaEntity.from(history));
    }

    @Override
    public Optional<History> findById(long id) {
        return historyJpaRepository.findById(id)
                .map(entity -> entity.toModel());
    }
}
