package com.gamsa.review.repository;

import com.gamsa.review.domain.Question;
import com.gamsa.review.entity.QuestionJpaEntity;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class QuestionRepositoryImpl implements QuestionRepository {

    private final QuestionJpaRepository questionJpaRepository;

    @Override
    public List<Question> findAllQuestion() {
        return questionJpaRepository.findAll().stream()
            .map(QuestionJpaEntity::toModel)
            .toList();
    }

    @Override
    public Optional<Question> findById(Integer questionId) {
        return questionJpaRepository.findById(questionId)
            .map(QuestionJpaEntity::toModel);
    }
}
