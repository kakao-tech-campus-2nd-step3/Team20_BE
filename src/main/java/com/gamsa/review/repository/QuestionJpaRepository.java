package com.gamsa.review.repository;

import com.gamsa.review.entity.QuestionJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionJpaRepository extends JpaRepository<QuestionJpaEntity, Integer> {

}
