package com.gamsa.review.stub;

import com.gamsa.review.domain.Answer;
import com.gamsa.review.repository.AnswerRepository;

public class StubAnswerRepository implements AnswerRepository {

    @Override
    public void save(Answer answer) {
        // do nothing
    }
}
