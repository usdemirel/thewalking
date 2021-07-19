package com.thewalking.shop.service;

import com.thewalking.shop.entity.ProductDescription;
import com.thewalking.shop.entity.Review;
import com.thewalking.shop.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ProductDescriptionService productDescriptionService;

    @Transactional
    @Override
    public Review save(Review review) {
        if(review.getProductDescription() != null){
            ProductDescription productDescription = productDescriptionService.findById(review.getProductDescription().getId()).get();
            double currentRating = productDescription.getRating();
            int currentReviewCount = productDescription.getReviewCount();
            double rating = (currentRating*currentReviewCount + review.getRating())/++currentReviewCount;
            productDescriptionService.setRatingAndReviewCountForProductDescription(rating,currentReviewCount,review.getProductDescription().getId());
        }
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> findAll() {
        List<Review> list = new ArrayList<>();
        reviewRepository.findAll().forEach(each -> list.add(each));
        return list;
    }

    @Override
    public Optional<Review> findById(Long id) {
        return reviewRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public List<Review> findAllByProductDescriptionIsNull() {
        return reviewRepository.findAllByProductDescriptionIsNull();
    }


}
