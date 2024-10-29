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
        avatarRepository.findById(id).orElseThrow(NoSuchElementException::new);
        avatarRepository.deleteById(id);
    }

    public AvatarFindResponse expUp(Long avatarId, int amount) {
        Avatar avatar = avatarRepository.findById(avatarId).orElseThrow(NoSuchElementException::new);
        avatar.expUp(amount);
        avatarRepository.save(avatar);
        return AvatarFindResponse.from(avatar);
    }

    public AvatarFindResponse update(Long avatarId, AvatarSaveRequest saveRequest) {
        Avatar avatar = avatarRepository.findById(avatarId).orElseThrow(NoSuchElementException::new);
        avatar.changeAgeRange(saveRequest.getAgeRange());
        avatar.changeExperience(saveRequest.getExperienced());
        avatar.changeNickname(saveRequest.getNickname());
        avatarRepository.save(avatar);
        return AvatarFindResponse.from(avatar);
    }
}
