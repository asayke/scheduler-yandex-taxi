package com.asayke.scheduleryandextaxi.scheduler;

import com.asayke.scheduleryandextaxi.model.Coordinate;
import com.asayke.scheduleryandextaxi.properties.CoordinateProperties;
import com.asayke.scheduleryandextaxi.service.TaxiService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class YandexScheduler {
    private final CoordinateProperties coordinateProperties;
    private final TaxiService taxiService;

    @Scheduled(fixedDelay = 30_000)
    public void updatePrice() {
        Coordinate startPoint = new Coordinate(coordinateProperties.getStartLongitude(), coordinateProperties.getStartLatitude());
        Coordinate endPoint = new Coordinate(coordinateProperties.getFinishLongitude(), coordinateProperties.getFinishLatitude());
        taxiService.getPrice(startPoint, endPoint);
    }
}