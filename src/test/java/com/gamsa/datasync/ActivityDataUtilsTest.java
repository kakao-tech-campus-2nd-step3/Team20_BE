package com.gamsa.datasync;

import com.gamsa.datasync.utils.ActivityDataUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class ActivityDataUtilsTest {
    ActivityDataUtils activityDataUtils;

    @BeforeEach
    void setUp() {
        activityDataUtils = new ActivityDataUtils();
    }

    @Test
    void byPeriod() {
        //given
        String today = "20241030";
        String afterday = "20241101";
        //when
        assertThat(activityDataUtils.getVolunteerParticipationList(today, afterday).size()).isNotZero();
    }
}
