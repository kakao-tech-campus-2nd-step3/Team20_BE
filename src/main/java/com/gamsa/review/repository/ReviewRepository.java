package com.gamsa.review.repository;

import com.gamsa.review.domain.Review;

import java.util.List;

public interface ReviewRepository {

    void save(Review review);

    List<Review> findReviews(Long instituteId, Long questionId);
}
