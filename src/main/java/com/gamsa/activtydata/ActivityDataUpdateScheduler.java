package com.gamsa.activtydata;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ActivityDataUpdateScheduler {
    private final ActivityDataUpdateService activityDataUpdateService;

    @Scheduled(cron = "0 1 0 * * *")
    private void runActivityDataUpdate() {
        activityDataUpdateService.update();
    }
}
