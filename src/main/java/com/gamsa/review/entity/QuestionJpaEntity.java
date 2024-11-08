package com.gamsa.review.entity;

import com.gamsa.review.domain.Question;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "question")
public class QuestionJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Integer questionId;

    @Column(name = "content", length = 255)
    private String content;

    public static QuestionJpaEntity from(Question question) {
        return QuestionJpaEntity.builder()
                .questionId(question.getQuestionId())
                .content(question.getContent())
                .build();
    }

    public Question toModel() {
        return Question.builder()
                .questionId(questionId)
                .content(content)
                .build();
    }
}
