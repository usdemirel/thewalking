package com.thewalking.shop.service;

import com.thewalking.shop.entity.ProductDescription;
import com.thewalking.shop.repository.ProductDescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductDescriptionServiceImpl implements ProductDescriptionService{

    @Autowired
    ProductDescriptionRepository productDescriptionRepository;

    @Override
    public Optional<ProductDescription> findById(Long aLong) {
        return productDescriptionRepository.findById(aLong);
    }

    @Override
    public ProductDescription save(ProductDescription productDescription) {
        return productDescriptionRepository.save(productDescription);
    }

    @Override
    public List<ProductDescription> findAll() {
        List<ProductDescription> list = new ArrayList<>();
        productDescriptionRepository.findAll().forEach(each -> list.add(each));
        return list;
    }

    @Override
    public void deleteById(Long aLong) {
        productDescriptionRepository.deleteById(aLong);
    }

    @Override
    public List<ProductDescription> findAllByTitleIsContainingOrKeyWordsIsContainingOrCategoriesIsContaining(String keyword) {
        return productDescriptionRepository.findAllByTitleIsContainingOrKeyWordsIsContainingOrCategoriesIsContaining(keyword,keyword,keyword);
    }

    @Override
    public int setRatingForProductDescription(Double rating, Long id) {
        return productDescriptionRepository.setRatingForProductDescription(rating,id);
    }

    @Override
    public int setRatingAndReviewCountForProductDescription(Double rating, Integer reviewCount, Long id) {
        return productDescriptionRepository.setRatingAndReviewCountForProductDescription(rating,reviewCount,id);
    }

    @Override
    public int setMinPriceAndMaxPriceForProductDescription(Double minPrice, Double maxPrice, Long id) {
        return productDescriptionRepository.setMinPriceAndMaxPriceForProductDescription(minPrice, maxPrice, id);
    }

    @Override
    public int setMinPriceForProductDescription(Double minPrice, Long id) {
        return productDescriptionRepository.setMinPriceForProductDescription(minPrice, id);
    }

    @Override
    public int setMaxPriceForProductDescription(Double maxPrice, Long id) {
        return productDescriptionRepository.setMinPriceForProductDescription(maxPrice, id);
    }
}
