package com.thewalking.shop.service;

import com.thewalking.shop.entity.ProductDescription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public interface ProductDescriptionService {

    Page<ProductDescription> findByTitleContaining(@RequestParam("name") String title, Pageable pageable);

    Page<ProductDescription> findAllByManyVarieties(String category, String title,double min, double max,Pageable pageable);

    Page<ProductDescription> findAllByCategoryCategory(String category, Pageable pageable);

    Optional<ProductDescription> findById(Long aLong);

    ProductDescription save(ProductDescription productDescription);

    List<ProductDescription> findAll();

    void deleteById(Long aLong);

    List<ProductDescription> findAllByTitleIsContainingOrKeyWordsIsContaining(String keyword);

    int setRatingForProductDescription(Double rating, Long id);

    int setRatingAndReviewCountForProductDescription(Double rating, Integer reviewCount, Long id);

    int setMinPriceAndMaxPriceForProductDescription(Double minPrice, Double maxPrice, Long id);

    int setMinPriceForProductDescription(Double minPrice, Long id);

    int setMaxPriceForProductDescription(Double maxPrice, Long id);




}
