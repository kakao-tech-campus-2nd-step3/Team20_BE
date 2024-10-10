package com.gamsa.activity.stub;

import com.gamsa.activity.domain.Activity;
import com.gamsa.activity.domain.District;
import com.gamsa.activity.domain.Institute;
import com.gamsa.activity.dto.ActivityFilterRequest;
import com.gamsa.activity.repository.ActivityRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

public class StubExistsActivityRepository implements ActivityRepository {

    private final District district = District.builder()
        .sidoCode(1234)
        .sidoGunguCode(8888)
        .sidoName("서울특별시")
        .gunguName("강남구")
        .sido(false)
        .build();

    private final Institute institute = Institute.builder()
        .instituteId(1L)
        .name("도서관")
        .location("서울시")
        .latitude(new BigDecimal("123456789.12341234"))
        .longitude(new BigDecimal("987654321.43214321"))
        .phone("010xxxxxxxx")
        .build();

    private final Activity activity = Activity.builder()
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
        .institute(institute)
        .sidoGungu(district)
        .build();

    @Override
    public void save(Activity activity) {
        // do nothing
    }

    @Override
    public Slice<Activity> findSlice(ActivityFilterRequest request, Pageable pageable) {
        return new SliceImpl<>(List.of(activity));
    }

    @Override
    public Optional<Activity> findById(Long activityId) {
        return Optional.of(activity);
    }
}
