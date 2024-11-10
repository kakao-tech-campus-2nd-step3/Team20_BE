package com.gamsa.dataupdate;

import static org.assertj.core.api.Assertions.assertThat;

import com.gamsa.dataupdate.utils.ActivityDataUtils;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ActivityDataUtilsTest {

    @Autowired
    private ActivityDataUtils activityDataUtils;

    @Test
    void 기간별_활동_리스트_조회() {
        //given
        LocalDate today = LocalDate.now();
        LocalDate endDate = today.plusDays(7);

        //when
        var list = activityDataUtils.getVolunteerParticipationList(today, endDate);

        //then
        System.out.println(list);
        assertThat(list.size()).isNotZero();
    }
}
