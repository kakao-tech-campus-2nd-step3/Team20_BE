package com.gamsa.review.repository;

import com.gamsa.review.domain.Review;
import com.gamsa.review.entity.ReviewJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ReviewRepositoryImpl implements ReviewRepository {
    private final ReviewJpaRepository reviewJpaRepository;

    @Override
    public void save(Review review) {
        reviewJpaRepository.save(ReviewJpaEntity.from(review));
    }

    @Override
    public List<Review> findReviews(Long instituteId, Long questionId) {
        return reviewJpaRepository.findByInstituteInstituteIdAndAnswersQuestionQuestionId(instituteId, questionId).stream().map(ReviewJpaEntity::toModel).toList();
    }


}
