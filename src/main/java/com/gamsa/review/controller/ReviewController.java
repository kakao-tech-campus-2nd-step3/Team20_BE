package com.gamsa.review.controller;

import com.gamsa.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/questions")
    public ResponseEntity<?> findAllQuestion() {
        return ResponseEntity.ok(reviewService.findAllResponse());
    }


}
