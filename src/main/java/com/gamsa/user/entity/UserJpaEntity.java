package com.gamsa.user.entity;

import com.gamsa.user.domain.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "users")
@Entity
public class UserJpaEntity {

    @Id
    @Column(name = "user_id")
    private Long id;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    public static UserJpaEntity from(User user) {
        return UserJpaEntity.builder()
            .id(user.getId())
            .nickname(user.getNickname())
            .build();
    }

    public User toModel() {
        return User.builder()
            .id(id)
            .nickname(nickname)
            .build();
    }
}
