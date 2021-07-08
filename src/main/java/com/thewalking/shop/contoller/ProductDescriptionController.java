package com.thewalking.shop.contoller;

import com.thewalking.shop.entity.ProductDescription;
import com.thewalking.shop.service.ProductDescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class ProductDescriptionController {

    @Autowired
    ProductDescriptionService productDescriptionService;

    @PreAuthorize("hasAnyRole('OWNER','MANAGER')")
    @RequestMapping(value="/productdescriptions", method = RequestMethod.POST)
    public ResponseEntity<ProductDescription> save(@RequestBody ProductDescription productDescription){
        try{
            return ResponseEntity.ok(productDescriptionService.save(productDescription));
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(),e);
        }
    }



}
