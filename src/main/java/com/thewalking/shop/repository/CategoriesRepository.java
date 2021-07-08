package com.thewalking.shop.repository;

import com.thewalking.shop.entity.Categories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends CrudRepository<Categories,Long> {
}
