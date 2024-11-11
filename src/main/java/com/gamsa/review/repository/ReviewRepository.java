package com.gamsa.review.repository;

import com.gamsa.review.domain.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository {

    void save(Review review);

    List<Review> findReviews(Long instituteId, int questionId);

    Optional<Review> findHistoryReview(Long historyId);
}
