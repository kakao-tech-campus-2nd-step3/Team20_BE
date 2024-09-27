package com.gamsa.avatar.domain;

import com.gamsa.avatar.constant.AgeRange;
import com.gamsa.avatar.constant.Experienced;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class Avatar {
    private Long avatarId;
    private Long avatarExp;
    private Long avatarLevel;
    private String nickname;
    private AgeRange ageRange;
    private Experienced experienced;
    private LocalDateTime updateDate;
}
