package com.asayke.scheduleryandextaxi.service;

import com.asayke.scheduleryandextaxi.apiclient.TaxiApiClient;
import com.asayke.scheduleryandextaxi.model.Coordinate;
import com.asayke.scheduleryandextaxi.model.MomentPrice;
import com.asayke.scheduleryandextaxi.model.Price;
import com.asayke.scheduleryandextaxi.properties.YandexProperties;
import com.asayke.scheduleryandextaxi.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaxiService {
    private final YandexProperties yandexProperties;
    private final PriceRepository priceRepository;
    private final TaxiApiClient taxiApiClient;

    public void getPrice(Coordinate startPoint, Coordinate endPoint) {
        String rll = startPoint.toString() + "~" + endPoint.toString();
        String clid = yandexProperties.getClid();
        String apiKey = yandexProperties.getApiKey();

        Price currentPrice = taxiApiClient.getPrice(clid, apiKey, rll);

        if (currentPrice.getOptions().isEmpty()) {
            throw new RuntimeException("Options are empty");
        }

        double priceDouble = currentPrice.getOptions().get(0).getPrice();
        MomentPrice momentPrice = new MomentPrice(LocalDateTime.now(ZoneId.of("Africa/Cairo")), priceDouble);

        priceRepository.save(momentPrice);
    }

    public List<MomentPrice> getAllPrice() {
        return priceRepository.findAll();
    }
}