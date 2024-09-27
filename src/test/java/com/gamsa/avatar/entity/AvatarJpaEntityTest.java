package com.gamsa.avatar.entity;

import static org.assertj.core.api.Assertions.assertThat;

import com.gamsa.avatar.constant.AgeRange;
import com.gamsa.avatar.constant.Experienced;
import com.gamsa.avatar.domain.Avatar;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class AvatarJpaEntityTest {
    @Test
    void 도메인에서_JPA엔티티로() {
        //given
        Avatar avatar = Avatar.builder()
                .avatarId(1L)
                .avatarLevel(1L)
                .avatarExp(1L)
                .nickname("닉네임")
                .ageRange(AgeRange.ADULT)
                .experienced(Experienced.NOVICE)
                .updateDate(LocalDateTime.now())
                .build();

        //when
        AvatarJpaEntity jpaEntity = AvatarJpaEntity.from(avatar);

        //then
        assertThat(jpaEntity.getAvatarId()).isEqualTo(avatar.getAvatarId());
    }

    @Test
    void JPA엔티티에서_도메인으로() {
        //given
        AvatarJpaEntity avatarJpaEntity = AvatarJpaEntity.builder()
                .avatarId(1L)
                .avatarLevel(1L)
                .avatarExp(1L)
                .nickname("닉네임")
                .ageRange(AgeRange.ADULT)
                .experienced(Experienced.NOVICE)
                .updateDate(LocalDateTime.now())
                .build();

        //when
        Avatar avatar = avatarJpaEntity.toAvatar();

        //then
        assertThat(avatar.getAvatarId()).isEqualTo(avatarJpaEntity.getAvatarId());
    }
}
