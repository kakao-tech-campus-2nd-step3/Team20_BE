package com.gamsa.history.controller;

import com.gamsa.history.dto.HistoryFindSliceResponse;
import com.gamsa.history.dto.HistorySaveRequest;
import com.gamsa.history.service.HistoryService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/histories")
public class HistoryController {

    private final HistoryService historyService;

    private static final int MAX_SIZE = Integer.MAX_VALUE - 1;

    @PostMapping
    public ResponseEntity<String> addHistory(@RequestBody HistorySaveRequest saveRequest,
        HttpServletRequest request) {

        Long userId = (Long) request.getAttribute("userId");
        historyService.save(saveRequest, userId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public Slice<HistoryFindSliceResponse> findSliceByUserId(
        @RequestParam(value = "page", required = false) Integer page,
        @RequestParam(value = "size", required = false) Integer size,
        HttpServletRequest request) {

        Long userId = (Long) request.getAttribute("userId");

        Pageable pageable;

        if (page == null || size == null) {
            pageable = PageRequest.of(0, MAX_SIZE, Sort.unsorted());
        } else {
            pageable = PageRequest.of(page, size, Sort.unsorted());
        }
        return historyService.findSliceByAvatarId(userId, pageable);
    }

    @DeleteMapping("{history-id}")
    public ResponseEntity<String> deleteHistory(@PathVariable("history-id") long historyId) {
        historyService.delete(historyId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
