package com.asayke.scheduleryandextaxi.service;

import com.asayke.scheduleryandextaxi.apiclient.TaxiApiClient;
import com.asayke.scheduleryandextaxi.model.Coordinate;
import com.asayke.scheduleryandextaxi.model.MomentPrice;
import com.asayke.scheduleryandextaxi.model.Price;
import com.asayke.scheduleryandextaxi.properties.YandexProperties;
import com.asayke.scheduleryandextaxi.repository.PriceRepository;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TaxiService {
    private final YandexProperties yandexProperties;
    private final PriceRepository priceRepository;
    private final TaxiApiClient taxiApiClient;
    private AtomicInteger price;

    public TaxiService(YandexProperties yandexProperties, PriceRepository priceRepository, TaxiApiClient taxiApiClient, MeterRegistry meterRegistry) {
        this.yandexProperties = yandexProperties;
        this.priceRepository = priceRepository;
        this.taxiApiClient = taxiApiClient;
        price = new AtomicInteger();
        meterRegistry.gauge("priceTaxi", price);
    }

    public void getPrice(Coordinate startPoint, Coordinate endPoint) {
        String rll = startPoint.toString() + "~" + endPoint.toString();
        String clid = yandexProperties.getClid();
        String apiKey = yandexProperties.getApiKey();

        Price currentPrice = taxiApiClient.getPrice(clid, apiKey, rll);

        if (currentPrice.getOptions().isEmpty()) {
            throw new RuntimeException("Options are empty");
        }

        double priceDouble = currentPrice.getOptions().get(0).getPrice();
        price.set((int) priceDouble);

        MomentPrice momentPrice = new MomentPrice(LocalDateTime.now(ZoneId.of("Africa/Cairo")), priceDouble);

        priceRepository.save(momentPrice);
    }

    public List<MomentPrice> getAllPrice() {
        return priceRepository.findAll();
    }
}