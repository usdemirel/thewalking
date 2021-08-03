package com.thewalking.shop.service;

import com.thewalking.shop.entity.ProductDescription;
import com.thewalking.shop.repository.ProductDescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductDescriptionServiceImpl implements ProductDescriptionService{

    @Autowired
    ProductDescriptionRepository productDescriptionRepository;

    @Override
    public Page<ProductDescription> findByTitleContaining(String title, Pageable pageable) {
        System.out.println(pageable.getPageNumber() + " " + pageable.getPageSize());
        return productDescriptionRepository.findByTitleContaining(title, pageable);
    }

    @Override
    public Page<ProductDescription> findAllByManyVarieties(String category, String title, double min, double max, Pageable pageable) {
        if(category.equals("") && title.equals("")) return productDescriptionRepository.findAllByMinPriceGreaterThanEqualAndMaxPriceLessThanEqual(min, max, pageable);
        else if (category.equals("")) return productDescriptionRepository.findAllByTitleIsContainingAndMinPriceGreaterThanEqualAndMaxPriceLessThanEqual(title, min, max, pageable);
        else if(min==0.0 & max==10000) return productDescriptionRepository.findAllByCategoryCategoryAndTitleIsContainingOrCategoryCategoryAndKeyWordsIsContaining(category,title,category,title,pageable);
        return productDescriptionRepository.findAllByCategoryCategoryAndTitleIsContainingAndMinPriceGreaterThanEqualAndMaxPriceLessThanEqual(category, title, min, max, pageable);
    }

    @Override
    public Page<ProductDescription> findAllByCategoryCategory(String category, Pageable pageable) {
        return productDescriptionRepository.findAllByCategoryCategory(category,pageable);
    }

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
    public List<ProductDescription> findAllByTitleIsContainingOrKeyWordsIsContaining(String keyword) {
        return productDescriptionRepository.findAllByTitleIsContainingOrKeyWordsIsContaining(keyword,keyword);
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
