package com.asayke.scheduleryandextaxi.repository;

import com.asayke.scheduleryandextaxi.model.MomentPrice;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PriceRepository extends CrudRepository<MomentPrice, Long> {
    List<MomentPrice> findAll();
}