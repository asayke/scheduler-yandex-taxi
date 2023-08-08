package com.asayke.scheduleryandextaxi.repository;

import com.asayke.scheduleryandextaxi.model.MomentPrice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends CrudRepository<MomentPrice, Long> {
}