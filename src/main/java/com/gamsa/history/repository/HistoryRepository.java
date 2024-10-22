package com.gamsa.history.repository;

import com.gamsa.history.domain.History;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.Optional;

public interface HistoryRepository {

    void save(History history);

    Optional<History> findById(long id);

    void delete(History history);

    Slice<History> findSliceByAvatarId(long avatarId, Pageable pageable);
}
