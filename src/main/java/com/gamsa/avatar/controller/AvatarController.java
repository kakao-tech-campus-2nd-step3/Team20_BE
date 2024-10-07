package com.gamsa.avatar.controller;


import com.gamsa.avatar.dto.AvatarFindResponse;
import com.gamsa.avatar.dto.AvatarSaveRequest;
import com.gamsa.avatar.service.AvatarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/avatar")
public class AvatarController {

    private final AvatarService avatarService;

    @PostMapping
    public ResponseEntity<String> saveAvatar(@RequestBody AvatarSaveRequest saveRequest) {
        avatarService.save(saveRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("{avatar-id}")
    public AvatarFindResponse getAvatar(@PathVariable Long avatarId) {
        return avatarService.findById(avatarId);
    }

    @PutMapping("{avatar-id}")
    public ResponseEntity<String> updateAvatar(@PathVariable Long avatarId, @RequestBody AvatarSaveRequest saveRequest) {
        avatarService.update(avatarId, saveRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{avatar-Id}")
    public ResponseEntity<String> deleteAvatar(@PathVariable Long avatarId) {
        avatarService.delete(avatarId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
