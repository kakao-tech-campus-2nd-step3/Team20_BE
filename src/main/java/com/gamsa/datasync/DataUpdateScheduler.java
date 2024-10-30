package com.gamsa.datasync;

import com.gamsa.datasync.service.ActivityDataUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataUpdateScheduler {
    private final ActivityDataUpdateService activityDataUpdateService;

    @Scheduled(cron = "0 1 0 * * *")
    private void runActivityDataUpdate() {
        activityDataUpdateService.update();
    }
}
