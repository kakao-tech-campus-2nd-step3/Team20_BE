package com.gamsa.review.repository;

import com.gamsa.review.domain.Question;
import java.util.List;
import java.util.Optional;

public interface QuestionRepository {

    List<Question> findAllQuestion();

    Optional<Question> findById(Integer questionId);
}
