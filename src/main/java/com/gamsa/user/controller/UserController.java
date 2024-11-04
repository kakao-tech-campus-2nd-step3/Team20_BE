package com.gamsa.user.controller;

import com.gamsa.user.dto.KakaoLoginResponse;
import com.gamsa.user.service.UserService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/login/kakao")
    public ResponseEntity<?> kakaoLogin(
        @RequestHeader Map<String, String> headers) {

        Map<String, Object> response = userService.userKakaoLogin(headers.get("token"));

        return ResponseEntity.ok()
            .header("token", (String) response.get("token"))
            .body((KakaoLoginResponse) response.get("body"));
    }
}
