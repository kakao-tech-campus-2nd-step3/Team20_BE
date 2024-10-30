package com.gamsa.history.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.gamsa.activity.constant.Category;
import com.gamsa.activity.domain.Activity;
import com.gamsa.activity.domain.District;
import com.gamsa.activity.domain.Institute;
import com.gamsa.activity.entity.ActivityJpaEntity;
import com.gamsa.avatar.constant.AgeRange;
import com.gamsa.avatar.constant.Experienced;
import com.gamsa.avatar.domain.Avatar;
import com.gamsa.avatar.entity.AvatarJpaEntity;
import com.gamsa.common.config.TestConfig;
import com.gamsa.history.constant.ActivityStatus;
import com.gamsa.history.entity.HistoryJpaEntity;
import com.gamsa.user.domain.User;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(TestConfig.class)
public class HistoryJpaRepositoryTest {
    @Autowired
    private HistoryJpaRepository historyJpaRepository;

    District district = District.builder()
        .sidoCode(1234)
        .sidoGunguCode(8888)
        .sidoName("서울특별시")
        .gunguName("강남구")
        .sido(false)
        .build();

    Institute institute = Institute.builder()
        .instituteId(1L)
        .name("도서관")
        .location("서울시")
        .latitude(new BigDecimal("123456789.12341234"))
        .longitude(new BigDecimal("987654321.43214321"))
        .sidoGungu(district)
        .phone("010xxxxxxxx")
        .build();

    Activity activity = Activity.builder()
        .actId(1L)
        .actTitle("어린이놀이안전관리 및 놀잇감 청결유지 및 정리")
        .actLocation("아이사랑꿈터 서구 5호점")
        .description("봉사 내용")
        .noticeStartDate(LocalDateTime.of(2024, 9, 10, 0, 0))
        .noticeEndDate(LocalDateTime.of(2024, 12, 7, 0, 0))
        .actStartDate(LocalDateTime.of(2024, 9, 10, 0, 0))
        .actEndDate(LocalDateTime.of(2024, 12, 7, 0, 0))
        .actStartTime(13)
        .actEndTime(18)
        .recruitTotalNum(1)
        .adultPossible(true)
        .teenPossible(false)
        .groupPossible(false)
        .actWeek(0111110)
        .actManager("윤순영")
        .actPhone("032-577-3026")
        .url("https://...")
        .category(Category.OTHER_ACTIVITIES)
        .institute(institute)
        .sidoGungu(district)
        .build();

    private final User user = User.builder()
        .id(1L)
        .nickname("nickname")
        .build();

    private final Avatar avatar = Avatar.builder()
        .avatarId(1L)
        .user(user)
        .avatarLevel(1L)
        .avatarExp(1L)
        .nickname("닉네임")
        .ageRange(AgeRange.ADULT)
        .experienced(Experienced.NOVICE)
        .build();

    private final HistoryJpaEntity historyJpaEntity = HistoryJpaEntity.builder()
        .historyId(1L)
        .activity(ActivityJpaEntity.from(activity))
        .avatar(AvatarJpaEntity.from(avatar))
        .activityStatus(ActivityStatus.APPLIED)
        .reviewed(false)
        .build();


    @Test
    void 새_기록_저장() {
        // when
        historyJpaRepository.save(historyJpaEntity);

        //then
        assertThat(historyJpaRepository.findById(1L).get().getHistoryId())
                .isEqualTo(historyJpaEntity.getHistoryId());
    }
}


