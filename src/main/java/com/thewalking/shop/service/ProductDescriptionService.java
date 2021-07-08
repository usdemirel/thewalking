package com.thewalking.shop.service;

import com.thewalking.shop.entity.ProductDescription;

import java.util.Optional;

public interface ProductDescriptionService {

    Optional<ProductDescription> findById(Long aLong);

    ProductDescription save(ProductDescription productDescription);

    Iterable<ProductDescription> findAll();

    void deleteById(Long aLong);
}
