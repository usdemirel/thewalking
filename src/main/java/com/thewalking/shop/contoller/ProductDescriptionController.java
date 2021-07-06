package com.thewalking.shop.contoller;

import com.thewalking.shop.service.ProductDescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductDescriptionController {

    @Autowired
    ProductDescriptionService productDescriptionService;



}
