package com.gamsa.avatar.dto;

import com.gamsa.avatar.constant.AgeRange;
import com.gamsa.avatar.constant.Experienced;
import com.gamsa.avatar.domain.Avatar;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@RequiredArgsConstructor
public class AvatarFindResponse {
    private final Long avatarId;
    private final Long avatarExp;
    private final Long avatarLevel;
    private final String nickName;
    private final AgeRange ageRange;
    private final Experienced exprienced;

    public static AvatarFindResponse from(Avatar avatar) {
        return AvatarFindResponse.builder()
                .avatarId(avatar.getAvatarId())
                .avatarExp(avatar.getAvatarExp())
                .avatarLevel(avatar.getAvatarLevel())
                .nickName(avatar.getNickname())
                .ageRange(avatar.getAgeRange())
                .exprienced(avatar.getExperienced())
                .build();
    }
}
