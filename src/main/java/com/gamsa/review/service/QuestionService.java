package com.gamsa.review.service;

import com.gamsa.review.domain.Question;
import com.gamsa.review.dto.QuestionResponse;
import com.gamsa.review.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<QuestionResponse> findAllResponse() {
        return questionRepository.findAllQuestion().stream()
                .map(QuestionResponse::from)
                .toList();
    }

    public List<Question> findAll() {
        return questionRepository.findAllQuestion();
    }
}
