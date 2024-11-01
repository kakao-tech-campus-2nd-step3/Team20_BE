package com.gamsa.dataupdate;

import com.gamsa.dataupdate.service.ActivityDataUpdateService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class DataUpdateSchedulerTest {

    @Autowired
    private DataUpdateScheduler dataUpdateScheduler;

    @MockBean
    private ActivityDataUpdateService activityDataUpdateService;

    @Test
    public void 업데이트_메서드_동작() {
        //given
        int days = 7;

        //when
        dataUpdateScheduler.runActivityDataUpdate();

        //then
        ArgumentCaptor<LocalDate> captor = ArgumentCaptor.forClass(LocalDate.class);
        verify(activityDataUpdateService).update(captor.capture(), captor.capture());

        LocalDate today = LocalDate.now();
        LocalDate expectedEndDate = today.plusDays(days);

        assertThat(captor.getAllValues())
                .hasSize(2)
                .containsExactly(today, expectedEndDate);
    }
}