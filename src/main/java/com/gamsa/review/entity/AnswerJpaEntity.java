package com.gamsa.review.entity;

import com.gamsa.review.domain.Answer;
import jakarta.persistence.*;
import lombok.*;

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

    @Column
    private int score;

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
