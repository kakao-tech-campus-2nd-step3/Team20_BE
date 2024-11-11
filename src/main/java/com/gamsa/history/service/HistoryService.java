package com.gamsa.history.service;

import com.gamsa.activity.domain.Activity;
import com.gamsa.activity.repository.ActivityRepository;
import com.gamsa.avatar.domain.Avatar;
import com.gamsa.avatar.repository.AvatarRepository;
import com.gamsa.history.domain.History;
import com.gamsa.history.dto.HistoryFindSliceResponse;
import com.gamsa.history.dto.HistorySaveRequest;
import com.gamsa.history.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class HistoryService {
    private final HistoryRepository historyRepository;
    private final AvatarRepository avatarRepository;
    private final ActivityRepository activityRepository;

    public void save(HistorySaveRequest saveRequest, Long userId) {

        Avatar avatar = avatarRepository.findByUserId(userId)
                .orElseThrow(NoSuchElementException::new);
        Activity activity = activityRepository.findById(saveRequest.getActId())
                .orElseThrow(NoSuchElementException::new);
        History history = saveRequest.toModel(avatar, activity);
        historyRepository.save(history);
    }

    public Slice<HistoryFindSliceResponse> findSliceByAvatarId(Long userId, Pageable pageable) {
        Avatar avatar = avatarRepository.findByUserId(userId)
                .orElseThrow(NoSuchElementException::new);

        Slice<History> histories = historyRepository
                .findSliceByAvatarId(avatar.getAvatarId(), pageable);
        histories.forEach(this::checkDate);

        return histories.map(HistoryFindSliceResponse::from);
    }

    public void delete(long historyId) {
        History history = historyRepository.findById(historyId).orElseThrow(NoSuchElementException::new);
        historyRepository.delete(history);
    }

    public void updateReviewed(long historyId, boolean isReviewed) {
        History history = historyRepository.findById(historyId).orElseThrow(NoSuchElementException::new);
        history.changeReviewed(isReviewed);
        historyRepository.save(history);
    }

    public History checkDate(History history) {
        LocalDateTime now = LocalDateTime.now();
        history.changeActivityStatusOnDate(now);
        historyRepository.save(history);
        return history;
    }
}
