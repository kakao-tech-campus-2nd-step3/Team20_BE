package com.gamsa.history.controller;

import com.gamsa.history.dto.HistoryFindSliceResponse;
import com.gamsa.history.dto.HistorySaveRequest;
import com.gamsa.history.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/history")
public class HistoryController {
    private HistoryService historyService;

    @PostMapping
    public ResponseEntity<String> addHistory(@RequestBody HistorySaveRequest saveRequest) {
        historyService.save(saveRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("{avatar-id}")
    public Slice<HistoryFindSliceResponse> findSliceByUserId(@PathVariable("avatar-id") long avatarId, Pageable pageable) {
        return historyService.findSliceByAvatarId(avatarId, pageable);
    }

    @DeleteMapping("{history-id}")
    public ResponseEntity<String> deleteHistory(@PathVariable("history-id") long historyId) {
        historyService.delete(historyId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
