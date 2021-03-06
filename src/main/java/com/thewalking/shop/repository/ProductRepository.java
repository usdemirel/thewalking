package com.thewalking.shop.repository;

import com.thewalking.shop.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
public interface ProductRepository extends CrudRepository<Product,Long> {
}
