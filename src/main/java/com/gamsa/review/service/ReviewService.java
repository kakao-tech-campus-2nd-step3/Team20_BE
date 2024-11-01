package com.gamsa.review.service;

import com.gamsa.review.dto.QuestionResponse;
import com.gamsa.review.repository.ReviewRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public List<QuestionResponse> findAllResponse() {
        return reviewRepository.findAllQuestion().stream()
            .map(QuestionResponse::from)
            .toList();
    }

}
