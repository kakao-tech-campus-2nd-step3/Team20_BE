package com.gamsa.avatar.domain;

import com.gamsa.avatar.constant.AgeRange;
import com.gamsa.avatar.constant.Experienced;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

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

    public void expUp(int amount) {
        this.avatarExp += amount;
        checkLevel();
    }

    public void checkLevel() {
        if(this.avatarExp >= this.avatarLevel * this.avatarLevel * 100) {
            avatarLevel += 1;
        }
    }

    public void changeExperience(Experienced experienced) {
        this.experienced = experienced;
    }

    public void changeAgeRange(AgeRange ageRange) {
        this.ageRange = ageRange;
    }

    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }
}
