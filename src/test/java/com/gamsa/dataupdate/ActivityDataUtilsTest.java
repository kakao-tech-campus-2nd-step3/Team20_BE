package com.gamsa.dataupdate;

import com.gamsa.activity.constant.Category;
import com.gamsa.dataupdate.utils.ActivityDataUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

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
        assertThat(list.size()).isNotZero();
    }

    @Test
    void 기관_상세_조회() {
        //given
        String programNo = "3168803";

        //when
        var result = activityDataUtils.getInstituteApiResponse(programNo);

        //then
        assertThat(result.getName()).isEqualTo("음성효심주간보호센터");
    }

    @Test
    void 활동_상세_조회() {
        //given
        String programNo = "3168803";

        //when
        var result = activityDataUtils.getVolunteerDetail(programNo);

        //then
        assertThat(result.getCategory()).isEqualTo(Category.CULTURE_ENVIRONMENT_AND_INTERNATIONAL_COOPERATION);
    }
}
