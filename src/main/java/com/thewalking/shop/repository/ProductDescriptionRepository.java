package com.thewalking.shop.repository;

import com.thewalking.shop.entity.ProductDescription;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductDescriptionRepository extends CrudRepository<ProductDescription,Long> {


}
