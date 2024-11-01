package com.gamsa.review.repository;

import com.gamsa.review.domain.Question;
import com.gamsa.review.entity.QuestionJpaEntity;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ReviewRepositoryImpl implements ReviewRepository {

    private final QuestionJpaRepository questionJpaRepository;

    @Override
    public List<Question> findAllQuestion() {
        return questionJpaRepository.findAll().stream()
            .map(QuestionJpaEntity::toModel)
            .toList();
    }
}
