package com.gamsa.avatar.service;

import com.gamsa.avatar.domain.Avatar;
import com.gamsa.avatar.dto.AvatarFindResponse;
import com.gamsa.avatar.dto.AvatarSaveRequest;
import com.gamsa.avatar.repository.AvatarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class AvatarService {
    private final AvatarRepository avatarRepository;

    public AvatarFindResponse findById(Long id) {
        Avatar avatar = avatarRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return AvatarFindResponse.from(avatar);
    }

    public void save(AvatarSaveRequest saveRequest) {
        Avatar avatar = saveRequest.toModel();
        avatarRepository.save(avatar);
    }

    public void delete(Long id) {
        avatarRepository.deleteById(id);
    }
}
