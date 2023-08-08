package com.asayke.scheduleryandextaxi.controller;

import com.asayke.scheduleryandextaxi.model.MomentPrice;
import com.asayke.scheduleryandextaxi.service.TaxiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PriceController {
    private final TaxiService taxiService;

    @GetMapping("/prices")
    public List<MomentPrice> getAllPrice() {
        return taxiService.getAllPrice();
    }
}