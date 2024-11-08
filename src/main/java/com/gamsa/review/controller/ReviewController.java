package com.gamsa.review.controller;


import com.gamsa.common.utils.ExtractUserIdFromJwt;
import com.gamsa.review.dto.ReviewSaveRequest;
import com.gamsa.review.service.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<?> saveReview(@RequestBody ReviewSaveRequest saveRequest,
        HttpServletRequest request) {
        Long userId = ExtractUserIdFromJwt.extract(request);
        reviewService.saveReview(userId, saveRequest);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
