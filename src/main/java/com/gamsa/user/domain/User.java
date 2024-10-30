package com.gamsa.user.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class User {

    private Long id;
    private String nickname;
}
