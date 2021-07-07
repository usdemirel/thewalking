package com.thewalking.shop.service;

import com.thewalking.shop.entity.ProductDescription;
import com.thewalking.shop.repository.ProductDescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductDescriptionServiceImpl implements ProductDescriptionService{

    @Autowired
    ProductDescriptionRepository productDescriptionRepository;


    @Override
    public Optional<ProductDescription> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public ProductDescription save(ProductDescription productDescription) {
        return productDescriptionRepository.save(productDescription);
    }

    @Override
    public Iterable<ProductDescription> findAll() {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }
}
