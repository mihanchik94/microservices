package ru.job4j.rservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@Configuration
public class SchedulerConfig {

    @Value("${scheduler.thread-cap}")
    private int threadCap;

    @Value("${scheduler.queued-task-cap}")
    private int queuedTaskCap;

    @Value("${scheduler.queued-task-cap}")
    private String name;

    @Bean
    public Scheduler blockingScheduler() {
        return Schedulers.newBoundedElastic(threadCap, queuedTaskCap, name);
    }
}