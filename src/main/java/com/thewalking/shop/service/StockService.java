package com.thewalking.shop.service;

import com.thewalking.shop.entity.Product;
import com.thewalking.shop.entity.ProductDescription;
import com.thewalking.shop.entity.Stock;

import java.util.List;
import java.util.Optional;

public interface StockService {

    Optional<Stock> findById(Long aLong);

    Stock save(Stock stock);

    List<Stock> findAll();

    void deleteById(Long aLong);


}
