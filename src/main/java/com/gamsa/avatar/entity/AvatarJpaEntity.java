package com.gamsa.avatar.entity;

import com.gamsa.avatar.constant.AgeRange;
import com.gamsa.avatar.constant.AgeRangeConverter;
import com.gamsa.avatar.constant.Experienced;
import com.gamsa.avatar.constant.ExperiencedConverter;
import com.gamsa.avatar.domain.Avatar;
import com.gamsa.common.entity.BaseEntity;
import com.gamsa.user.entity.UserJpaEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@Entity
@Table(name = "Avatar")
@AllArgsConstructor
@NoArgsConstructor
public class AvatarJpaEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "avatar_id")
    private Long avatarId;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private UserJpaEntity user;

    @Column(name = "avatar_exp")
    private Long avatarExp;

    @Column(name = "avatar_level")
    private Long avatarLevel;

    @Column(name = "nickname")
    private String nickname;

    @Convert(converter = AgeRangeConverter.class)
    @Column(name = "age_range")
    private AgeRange ageRange;

    @Convert(converter = ExperiencedConverter.class)
    @Column(name = "experienced")
    private Experienced experienced;

    public static AvatarJpaEntity from(Avatar avatar) {
        return AvatarJpaEntity.builder()
            .avatarId(avatar.getAvatarId())
            .user(UserJpaEntity.from(avatar.getUser()))
            .avatarExp(avatar.getAvatarExp())
            .avatarLevel(avatar.getAvatarLevel())
            .nickname(avatar.getNickname())
            .ageRange(avatar.getAgeRange())
            .experienced(avatar.getExperienced())
            .build();
    }

    public Avatar toModel() {
        return Avatar.builder()
            .avatarId(avatarId)
            .user(user.toModel())
            .avatarExp(avatarExp)
            .avatarLevel(avatarLevel)
            .nickname(nickname)
            .ageRange(ageRange)
            .experienced(experienced)
            .build();
    }
}
