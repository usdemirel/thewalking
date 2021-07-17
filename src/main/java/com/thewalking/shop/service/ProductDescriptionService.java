package com.thewalking.shop.service;

import com.thewalking.shop.entity.ProductDescription;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductDescriptionService {

    Optional<ProductDescription> findById(Long aLong);

    ProductDescription save(ProductDescription productDescription);

    List<ProductDescription> findAll();

    void deleteById(Long aLong);

    List<ProductDescription> findAllByTitleIsContainingOrKeyWordsIsContainingOrCategoriesIsContaining(String keyword);

    int setRatingForProductDescription(Double rating, Long id);

    int setRatingAndReviewCountForProductDescription(Double rating, Integer reviewCount, Long id);

    int setMinPriceAndMaxPriceForProductDescription(Double minPrice, Double maxPrice, Long id);

    int setMinPriceForProductDescription(Double minPrice, Long id);

    int setMaxPriceForProductDescription(Double maxPrice, Long id);




}
