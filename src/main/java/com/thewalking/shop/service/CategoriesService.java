package com.thewalking.shop.service;

import com.thewalking.shop.entity.Categories;
import com.thewalking.shop.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface CategoriesService {

    Categories save(Categories categories);
    Iterable<Categories> findAll();
    Optional<Categories> findById(Long id);
    void deleteById(Long id);




}
