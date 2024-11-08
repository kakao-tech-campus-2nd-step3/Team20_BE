package com.gamsa.review.service;

import com.gamsa.review.dto.QuestionResponse;
import com.gamsa.review.repository.QuestionRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<QuestionResponse> findAllResponse() {
        return questionRepository.findAllQuestion().stream()
            .map(QuestionResponse::from)
            .toList();
    }

}
