package com.gamsa.review.repository;

import com.gamsa.review.entity.ReviewJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewJpaRepository extends JpaRepository<ReviewJpaEntity, Long> {
    List<ReviewJpaEntity> findByInstituteInstituteIdAndAnswersQuestionQuestionId(Long instituteId, Long questionId);

}
