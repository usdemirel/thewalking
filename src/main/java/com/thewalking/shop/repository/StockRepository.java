package com.thewalking.shop.repository;

import com.thewalking.shop.entity.Stock;
import org.springframework.data.repository.CrudRepository;

public interface StockRepository extends CrudRepository<Stock,Long> {
}
