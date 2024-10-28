package com.gamsa.avatar.service;

import com.gamsa.avatar.domain.Avatar;
import com.gamsa.avatar.dto.AvatarFindResponse;
import com.gamsa.avatar.dto.AvatarSaveRequest;
import com.gamsa.avatar.repository.AvatarRepository;
import com.gamsa.user.entity.UserJpaEntity;
import com.gamsa.user.repository.UserRepository;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AvatarService {
    private final AvatarRepository avatarRepository;
    private final UserRepository userRepository;

    public AvatarFindResponse findByUserId(Long userId) {
        Avatar avatar = avatarRepository.findByUserId(userId)
            .orElseThrow(NoSuchElementException::new);
        return AvatarFindResponse.from(avatar);
    }

    public void save(AvatarSaveRequest saveRequest, Long userId) {
        UserJpaEntity user = userRepository.findById(userId)
            .orElseThrow(() -> new NoSuchElementException("존재하지 않는 유저."));

        Avatar newAvatar = saveRequest.toModel(user);
        avatarRepository.findByNickname(newAvatar.getNickname())
            .ifPresent(avatar -> {
                throw new IllegalArgumentException("이미 존재하는 닉네임");
            });
        avatarRepository.save(newAvatar);
    }

    public void delete(Long userId) {
        Avatar avatar = avatarRepository.findByUserId(userId)
            .orElseThrow(NoSuchElementException::new);
        avatarRepository.deleteById(avatar.getAvatarId());
    }

    public AvatarFindResponse expUp(Long userId, int amount) {
        Avatar avatar = avatarRepository.findByUserId(userId)
            .orElseThrow(NoSuchElementException::new);
        avatar.expUp(amount);
        avatarRepository.save(avatar);
        return AvatarFindResponse.from(avatar);
    }

    public AvatarFindResponse update(AvatarSaveRequest saveRequest, Long userId) {
        Avatar avatar = avatarRepository.findByUserId(userId)
            .orElseThrow(NoSuchElementException::new);
        avatar.changeAgeRange(saveRequest.getAgeRange());
        avatar.changeExperience(saveRequest.getExperienced());
        avatar.changeNickname(saveRequest.getNickname());
        avatarRepository.save(avatar);
        return AvatarFindResponse.from(avatar);
    }
}
