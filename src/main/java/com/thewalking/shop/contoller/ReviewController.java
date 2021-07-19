package com.thewalking.shop.contoller;

import com.thewalking.shop.entity.Review;
import com.thewalking.shop.exception.ErrorMessages;
import com.thewalking.shop.exception.ReviewException;
import com.thewalking.shop.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/api/reviews")
@RestController
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @PreAuthorize("hasAnyRole('CUSTOMER','MANAGER','OWNER')")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Review> save(@RequestBody Review review){
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.save(review));
    }
    @PreAuthorize("hasAnyRole('MANAGER','OWNER')")
    @RequestMapping(value="", method= RequestMethod.GET)
    public ResponseEntity<List<Review>> findALl(){
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.findAll());
    }

    @PreAuthorize("hasAnyRole('MANAGER','OWNER')")
    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<Review> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.findById(id).orElseThrow(
                new ReviewException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage())
        ));
    }

    @PreAuthorize("hasAnyRole('MANAGER','OWNER')")
    @RequestMapping(value="/company/all", method= RequestMethod.GET)
    public ResponseEntity<List<Review>> findAllCompanyReviews(){
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.findAllByProductDescriptionIsNull());
    }

}
