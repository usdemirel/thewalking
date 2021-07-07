package com.thewalking.shop.service;

import com.thewalking.shop.entity.Product;
import com.thewalking.shop.entity.ProductDescription;

import java.util.Optional;

public interface ProductService {

    Optional<Product> findById(Long aLong);

    Product save(Product product);

    Iterable<Product> findAll();

    void deleteById(Long aLong);


}
