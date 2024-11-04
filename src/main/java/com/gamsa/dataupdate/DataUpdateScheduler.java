package com.gamsa.dataupdate;

import com.gamsa.dataupdate.service.ActivityDataUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class DataUpdateScheduler {
    private final ActivityDataUpdateService activityDataUpdateService;

    @Value("${spring.openapi.days}")
    private int days;

    @Scheduled(cron = "0 1 0 * * *")
    public void runActivityDataUpdate() {
        LocalDate today = LocalDate.now();
        LocalDate endDate = today.plusDays(days);

        activityDataUpdateService.update(today, endDate);
    }
}
