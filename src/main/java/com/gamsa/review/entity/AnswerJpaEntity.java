package com.gamsa.review.entity;

import com.gamsa.review.domain.Answer;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "answer")
public class AnswerJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private Long answerId;

    @Column(name = "score")
    private double score;

    @Column(name = "review_id")
    private Long reviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private QuestionJpaEntity question;

    public static AnswerJpaEntity from(Answer answer) {
        return AnswerJpaEntity.builder()
                .answerId(answer.getAnswerId())
                .score(answer.getScore())
                .question(QuestionJpaEntity.from(answer.getQuestion()))
                .build();
    }

    public Answer toModel() {
        return Answer.builder()
                .answerId(answerId)
                .score(score)
                .question(question.toModel())
                .build();
    }
}
