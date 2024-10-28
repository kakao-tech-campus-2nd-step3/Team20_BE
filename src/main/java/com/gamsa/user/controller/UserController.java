package com.gamsa.user.controller;

import com.gamsa.user.service.UserService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/login/kakao")
    public ResponseEntity<String> kakaoLogin(@RequestBody Map<String, String> kakaoToken) {

        String accessToken = userService.userKakaoLogin(kakaoToken.get("token"));
        return ResponseEntity.ok()
            .header("token", accessToken)
            .build();
    }
}
