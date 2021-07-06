package com.thewalking.shop.service;

import com.thewalking.shop.repository.ProductDescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductDescriptionServiceImpl implements ProductDescriptionService{

    @Autowired
    ProductDescriptionRepository productDescriptionRepository;


}
