package com.gamsa.review.entity;

import com.gamsa.activity.entity.ActivityJpaEntity;
import com.gamsa.activity.entity.InstituteJpaEntity;
import com.gamsa.history.entity.HistoryJpaEntity;
import com.gamsa.review.domain.Review;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
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
@Table(name = "review")
public class ReviewJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long reviewId;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "review_id")
    private List<AnswerJpaEntity> answers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_id")
    private ActivityJpaEntity activity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "institute_id")
    private InstituteJpaEntity institute;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "history_id")
    private HistoryJpaEntity history;

    public static ReviewJpaEntity from(Review review) {
        return ReviewJpaEntity.builder()
                .reviewId(review.getReviewId())
                .answers(review.getAnswers().stream().map(answer -> AnswerJpaEntity.from(answer)).toList())
                .activity(ActivityJpaEntity.from(review.getActivity()))
                .institute(InstituteJpaEntity.from(review.getInstitute()))
                .history(HistoryJpaEntity.from(review.getHistory()))
                .build();
    }

    public Review toModel() {
        return Review.builder()
                .reviewId(reviewId)
                .answers(answers.stream().map(AnswerJpaEntity::toModel).toList())
                .activity(activity.toModel())
                .institute(institute.toModel())
                .history(history.toModel())
                .build();

    }
}
