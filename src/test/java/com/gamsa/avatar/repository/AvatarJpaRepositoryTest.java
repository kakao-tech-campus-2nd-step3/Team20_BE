package com.gamsa.avatar.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.gamsa.avatar.constant.AgeRange;
import com.gamsa.avatar.constant.Experienced;
import com.gamsa.avatar.entity.AvatarJpaEntity;
import com.gamsa.common.config.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;

@DataJpaTest
public class AvatarJpaRepositoryTest {
    @Autowired
    private AvatarJpaRepository avatarJpaRepository;

    private final AvatarJpaEntity jpaEntity = AvatarJpaEntity.builder()
            .avatarId(1L)
            .avatarLevel(1L)
            .avatarExp(1L)
            .nickname("닉네임")
            .ageRange(AgeRange.ADULT)
            .experienced(Experienced.NOVICE)
            .build();

    @Test
    void 새로운_유저_저장() {
        //when
        avatarJpaRepository.save(jpaEntity);

        //then
        assertThat(avatarJpaRepository.findById(1L).get().getNickname()).isEqualTo(jpaEntity.getNickname());
    }

    @Test
    void 저장된_유저_삭제() {
        //given
        avatarJpaRepository.save(jpaEntity);

        //when
        avatarJpaRepository.deleteById(1L);

        //then
        assertThat(avatarJpaRepository.findById(1L)).isEmpty();
    }
}
