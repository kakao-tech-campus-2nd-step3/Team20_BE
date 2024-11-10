package com.gamsa.review.repository;

import com.gamsa.review.domain.Answer;
import com.gamsa.review.entity.AnswerJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class AnswerRepositoryImpl implements AnswerRepository {
    private final AnswerJpaRepository answerJpaRepository;

    @Override
    public void save(Answer answer) {
        answerJpaRepository.save(AnswerJpaEntity.from(answer));
    }
}
