package com.asayke.scheduleryandextaxi.repository;

import com.asayke.scheduleryandextaxi.model.MomentPrice;
import io.micrometer.core.annotation.Timed;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PriceRepository extends CrudRepository<MomentPrice, Long> {
    @Timed("gettingAllPrices")
    List<MomentPrice> findAll();
}