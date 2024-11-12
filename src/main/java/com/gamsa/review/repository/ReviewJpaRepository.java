package com.gamsa.review.repository;

import com.gamsa.review.entity.ReviewJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewJpaRepository extends JpaRepository<ReviewJpaEntity, Long> {
    List<ReviewJpaEntity> findByInstituteInstituteIdAndAnswersQuestionQuestionId(Long instituteId, int questionId);

    Optional<ReviewJpaEntity> findByHistoryHistoryId(long historyHistoryId);
}
