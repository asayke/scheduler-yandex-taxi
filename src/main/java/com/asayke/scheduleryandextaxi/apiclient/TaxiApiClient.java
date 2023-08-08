package com.asayke.scheduleryandextaxi.apiclient;

import com.asayke.scheduleryandextaxi.model.Price;
import io.micrometer.core.annotation.Timed;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "yandexclient", url = "${yandex.url}")
public interface TaxiApiClient {
    @Timed("gettingPriceFromYandex")
    @GetMapping
    Price getPrice(@RequestParam String clid, @RequestParam String apiKey, @RequestParam String rll);
}