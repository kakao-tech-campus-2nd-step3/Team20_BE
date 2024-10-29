package com.gamsa.avatar.entity;

import static org.assertj.core.api.Assertions.assertThat;

import com.gamsa.avatar.constant.AgeRange;
import com.gamsa.avatar.constant.Experienced;
import com.gamsa.avatar.domain.Avatar;
import com.gamsa.user.domain.User;
import com.gamsa.user.entity.UserJpaEntity;
import org.junit.jupiter.api.Test;

public class AvatarJpaEntityTest {
    @Test
    void 도메인에서_JPA엔티티로() {
        //given
        User user = User.builder()
            .id(1L)
            .nickname("nickname")
            .build();
        Avatar avatar = Avatar.builder()
            .avatarId(1L)
            .user(user)
            .avatarLevel(1L)
            .avatarExp(1L)
            .nickname("닉네임")
            .ageRange(AgeRange.ADULT)
            .experienced(Experienced.NOVICE)
            .build();

        //when
        AvatarJpaEntity jpaEntity = AvatarJpaEntity.from(avatar);

        //then
        assertThat(jpaEntity.getAvatarId()).isEqualTo(avatar.getAvatarId());
    }

    @Test
    void JPA엔티티에서_도메인으로() {
        //given
        UserJpaEntity userJpaEntity = UserJpaEntity.builder()
            .id(1L)
            .nickname("nickname")
            .build();
        AvatarJpaEntity avatarJpaEntity = AvatarJpaEntity.builder()
            .avatarId(1L)
            .user(userJpaEntity)
            .avatarLevel(1L)
            .avatarExp(1L)
            .nickname("닉네임")
            .ageRange(AgeRange.ADULT)
            .experienced(Experienced.NOVICE)
            .build();

        //when
        Avatar avatar = avatarJpaEntity.toModel();

        //then
        assertThat(avatar.getAvatarId()).isEqualTo(avatarJpaEntity.getAvatarId());
    }
}
