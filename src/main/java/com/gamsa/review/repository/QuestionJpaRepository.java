package com.gamsa.review.repository;

import com.gamsa.review.domain.Answer;
import com.gamsa.review.entity.QuestionJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionJpaRepository extends JpaRepository<QuestionJpaEntity, Integer> {

    public void save(Answer answer);
}
