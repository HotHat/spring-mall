package com.lyhux.mall.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SpringScheduleConfig {

    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask() {
        System.out.println(
                "Schedule fixed delay task - " + System.currentTimeMillis() / 1000);
    }

    @Scheduled(fixedRate = 2000)
    public void scheduleFixedRateTask() {
        System.out.println(
                "Schedule fixed rate task - " + System.currentTimeMillis() / 1000);
    }

    @Scheduled(fixedDelay = 5000, initialDelay = 1000)
    public void scheduleFixedRateWithInitialDelayTask() {

        long now = System.currentTimeMillis() / 1000;
        System.out.println(
                "Schedule fixed rate task with one second initial delay - " + now);
    }

    @Scheduled(cron = "3 * * * * ?", zone = "Asia/Shanghai")
    public void scheduleTaskUsingCronExpression() {

        long now = System.currentTimeMillis() / 1000;
        System.out.println(
                "schedule tasks using cron jobs - " + now);
    }


}
