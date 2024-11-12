package com.gamsa.review.repository;

import com.gamsa.review.domain.Review;
import com.gamsa.review.entity.ReviewJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ReviewRepositoryImpl implements ReviewRepository {
    private final ReviewJpaRepository reviewJpaRepository;

    @Override
    public void save(Review review) {
        reviewJpaRepository.save(ReviewJpaEntity.from(review));
    }

    @Override
    public List<Review> findReviews(Long instituteId, int questionId) {
        return reviewJpaRepository.findByInstituteInstituteIdAndAnswersQuestionQuestionId(instituteId, questionId).stream()
                .map(ReviewJpaEntity::toModel).toList();
    }

    @Override
    public Optional<Review> findHistoryReview(Long historyId) {
        return reviewJpaRepository.findByHistoryHistoryId(historyId).map(ReviewJpaEntity::toModel);
    }

}
