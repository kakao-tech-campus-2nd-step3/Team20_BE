package com.gamsa.user.dto;

import com.gamsa.avatar.dto.AvatarFindResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class KakaoLoginResponse {

    private final AvatarFindResponse avatar;
}
