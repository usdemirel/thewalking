package com.thewalking.shop.service;

import com.thewalking.shop.dto.ProductsTotal;
import com.thewalking.shop.entity.Product;
import com.thewalking.shop.entity.ProductDescription;
import com.thewalking.shop.entity.Stock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface StockService {

    Optional<Stock> findById(Long aLong);

    Stock save(Stock stock);

    List<Stock> findAll();

    void deleteById(Long aLong);

    int setPriceForStock(Double price, Long Id);

    int addQuantityForStock(Integer quantity, Long Id);

    int setSalesPriceAndSalesStartDateAndSalesEndDateForStock(Double salesPrice, LocalDate salesStartDate, LocalDate salesEndDate, Long Id);

    List<ProductsTotal> findProductsInAllStoresBySKU();


}
