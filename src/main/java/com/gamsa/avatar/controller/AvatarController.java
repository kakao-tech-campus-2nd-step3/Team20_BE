package com.gamsa.avatar.controller;


import com.gamsa.avatar.dto.AvatarFindResponse;
import com.gamsa.avatar.dto.AvatarSaveRequest;
import com.gamsa.avatar.service.AvatarService;
import com.gamsa.common.utils.ExtractUserIdFromJwt;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/avatars")
public class AvatarController {

    private final AvatarService avatarService;

    @PostMapping
    public ResponseEntity<String> saveAvatar(@RequestBody AvatarSaveRequest saveRequest,
        HttpServletRequest request) {

        Long userId = ExtractUserIdFromJwt.extract(request);
        avatarService.save(saveRequest, userId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public AvatarFindResponse getAvatar(HttpServletRequest request) {

        Long userId = ExtractUserIdFromJwt.extract(request);
        return avatarService.findByUserId(userId);
    }

    @PutMapping
    public ResponseEntity<String> updateAvatar(@RequestBody AvatarSaveRequest saveRequest,
        HttpServletRequest request) {
        Long userId = ExtractUserIdFromJwt.extract(request);
        avatarService.update(saveRequest, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAvatar(HttpServletRequest request) {
        Long userId = ExtractUserIdFromJwt.extract(request);
        avatarService.delete(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
