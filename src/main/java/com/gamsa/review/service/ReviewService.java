package com.gamsa.review.service;

import com.gamsa.activity.domain.Activity;
import com.gamsa.avatar.domain.Avatar;
import com.gamsa.history.domain.History;
import com.gamsa.history.repository.HistoryRepository;
import com.gamsa.review.domain.Answer;
import com.gamsa.review.domain.Question;
import com.gamsa.review.domain.Review;
import com.gamsa.review.dto.ReviewSaveRequest;
import com.gamsa.review.repository.AnswerRepository;
import com.gamsa.review.repository.QuestionRepository;
import com.gamsa.review.repository.ReviewRepository;
import com.gamsa.user.repository.UserRepository;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;
    private final ReviewRepository reviewRepository;
    private final AnswerRepository answerRepository;
    private final HistoryRepository historyRepository;

    @Transactional
    public void saveReview(Long userId, ReviewSaveRequest saveRequest) {

        userRepository.findById(userId)
            .orElseThrow((() -> new NoSuchElementException("존재하지 않는 계정")));

        History history = historyRepository.findById(saveRequest.getHistoryId())
            .orElseThrow(() -> new NoSuchElementException("존재하지 않는 기록"));

        Avatar avatar = history.getAvatar();
        Activity activity = history.getActivity();

        // 리뷰 여부 체크
        history.changeReviewed(true);
        historyRepository.save(history);

        // save review
        Review review = Review.builder()
            .avatar(avatar)
            .history(history)
            .institute(activity.getInstitute())
            .build();

        reviewRepository.save(review);

        // save answers
        List<Answer> answers = saveRequest.getAnswers().stream()
            .map(answer -> {
                Question question = questionRepository.findById(answer.getQuestionId())
                    .orElseThrow(() -> new NoSuchElementException("존재하지 않는 질문"));

                return Answer.builder()
                    .question(question)
                    .score(answer.getScore())
                    .build();
            })
            .toList();

        answers.forEach(answerRepository::save);
    }
}
