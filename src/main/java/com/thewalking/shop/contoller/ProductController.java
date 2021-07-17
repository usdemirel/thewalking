package com.thewalking.shop.contoller;

import com.thewalking.shop.entity.Product;
import com.thewalking.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RequestMapping("/api/products")
@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @PreAuthorize("hasAnyRole('OWNER','MANAGER','EMPLOYEE')")
    @RequestMapping(value="", method = RequestMethod.POST)
    public ResponseEntity<Product> save(@RequestBody Product product){
        try{
            return ResponseEntity.ok(productService.save(product));
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(),e);
        }
    }
}
