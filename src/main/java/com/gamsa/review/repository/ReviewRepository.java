package com.gamsa.review.repository;

import com.gamsa.review.domain.Question;
import java.util.List;

public interface ReviewRepository {

    List<Question> findAllQuestion();
}
