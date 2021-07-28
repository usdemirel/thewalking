package com.thewalking.shop.repository;

import com.thewalking.shop.entity.Product;
import com.thewalking.shop.entity.ProductDescription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@CrossOrigin
public interface ProductDescriptionRepository extends CrudRepository<ProductDescription,Long> {
    List<ProductDescription> findAllByTitleIsContainingOrKeyWordsIsContainingOrCategoriesIsContaining(String title,String keyword, String category);

    Page<ProductDescription> findByTitleContaining(@RequestParam("name") String title, Pageable pageable);


    @Modifying
    @Query("update ProductDescription pd set pd.rating = ?1 where pd.id = ?2")
    int setRatingForProductDescription(Double rating, Long id);

    @Modifying
    @Query("update ProductDescription pd set pd.rating = ?1, pd.reviewCount = ?2 where pd.id = ?3")
    int setRatingAndReviewCountForProductDescription(Double rating, Integer reviewCount, Long id);

    @Modifying
    @Query("update ProductDescription pd set pd.minPrice = ?1, pd.maxPrice = ?2 where pd.id = ?3")
    int setMinPriceAndMaxPriceForProductDescription(Double minPrice, Double maxPrice, Long id);

    @Modifying
    @Query("update ProductDescription pd set pd.minPrice = ?1 where pd.id = ?2")
    int setMinPriceForProductDescription(Double minPrice, Long id);

    @Modifying
    @Query("update ProductDescription pd set pd.maxPrice = ?1 where pd.id = ?2")
    int setMaxPriceForProductDescription(Double maxPrice, Long id);

}
