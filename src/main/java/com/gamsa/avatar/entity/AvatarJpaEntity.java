package com.gamsa.avatar.entity;

import com.gamsa.avatar.constant.AgeRange;
import com.gamsa.avatar.constant.AgeRangeConverter;
import com.gamsa.avatar.constant.Experienced;
import com.gamsa.avatar.constant.ExperiencedConverter;
import com.gamsa.avatar.domain.Avatar;
import com.gamsa.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@Table(name = "Avatar")
@AllArgsConstructor
@NoArgsConstructor
public class AvatarJpaEntity extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long avatarId;

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
                .avatarExp(avatarExp)
                .avatarLevel(avatarLevel)
                .nickname(nickname)
                .ageRange(ageRange)
                .experienced(experienced)
                .build();
    }
}
