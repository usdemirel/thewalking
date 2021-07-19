package com.thewalking.shop.service;

import com.thewalking.shop.entity.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    Review save(Review review);
    List<Review> findAll();
    Optional<Review> findById(Long id);
    void deleteById(Long id);
    List<Review> findAllByProductDescriptionIsNull();

}
