package com.thewalking.shop.contoller;

import com.thewalking.shop.entity.Review;
import com.thewalking.shop.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {
    @Autowired
    ReviewService reviewService;
    @RequestMapping(value = "/reviews", method = RequestMethod.POST)
    public ResponseEntity<Review> save(@RequestBody Review review){
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.save(review));
    }

}
