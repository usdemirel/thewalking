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
}
