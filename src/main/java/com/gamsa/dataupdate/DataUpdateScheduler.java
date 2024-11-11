package com.gamsa.dataupdate;

import com.gamsa.dataupdate.service.ActivityDataUpdateService;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataUpdateScheduler {
    private final ActivityDataUpdateService activityDataUpdateService;

    @Value("${openapi.days}")
    private int days;

    @Scheduled(cron = "0 0 0 * * *")
    public void runActivityDataUpdate() {
        LocalDate today = LocalDate.now();
        LocalDate endDate = today.plusDays(days);

        activityDataUpdateService.update(today, endDate);
    }

}
