package com.asayke.scheduleryandextaxi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EnableConfigurationProperties
public class SchedulerYandexTaxiApplication {
    public static void main(String[] args) {
        SpringApplication.run(SchedulerYandexTaxiApplication.class, args);
    }
}