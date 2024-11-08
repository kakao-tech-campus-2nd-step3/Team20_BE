package com.gamsa.review.stub;

import com.gamsa.review.domain.Question;
import com.gamsa.review.repository.QuestionRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class StubQuestionRepository implements QuestionRepository {

    Question question1 = Question.builder()
            .questionId(1)
            .content("질문 테스트1")
            .build();

    Question question2 = Question.builder()
            .questionId(2)
            .content("질문 테스트2")
            .build();

    List<Question> questions = Arrays.asList(question1, question2);

    @Override
    public List<Question> findAllQuestion() {
        return questions;
    }

    @Override
    public Optional<Question> findById(Integer id) {
        return Optional.of(question1);
    }

}
