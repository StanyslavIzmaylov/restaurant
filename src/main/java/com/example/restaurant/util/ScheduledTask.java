package com.example.restaurant.util;

import com.example.restaurant.repository.menu.DataJpaMenuRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {

    private final DataJpaMenuRepository dataJpaMenuRepository;

    public ScheduledTask(DataJpaMenuRepository dataJpaMenuRepository) {
        this.dataJpaMenuRepository = dataJpaMenuRepository;
    }

    @Scheduled(cron = "59 59 23 * * ?")
    public void deleteAllEveryDay() {
        dataJpaMenuRepository.deleteAllEveryDay();
    }
}
