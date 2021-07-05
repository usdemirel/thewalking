package com.thewalking.shop.repository;

import com.thewalking.shop.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product,Long> {
}
