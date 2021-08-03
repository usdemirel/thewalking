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

@CrossOrigin(origins = "http://localhost:4200/")
public interface ProductDescriptionRepository extends CrudRepository<ProductDescription,Long> {
    List<ProductDescription> findAllByTitleIsContainingOrKeyWordsIsContaining(String title,String keyword);

    Page<ProductDescription> findByTitleContaining(@RequestParam("name") String title, Pageable pageable);
    Page<ProductDescription> findAllByCategoryCategoryAndTitleIsContainingOrCategoryCategoryAndKeyWordsIsContaining(String category1, String title, String category2, String keyword, Pageable pageable);
    Page<ProductDescription> findAllByCategoryCategory(String category, Pageable pageable);
    Page<ProductDescription> findAllByCategoryCategoryAndTitleIsContainingAndMinPriceGreaterThanEqualAndMaxPriceLessThanEqual(String category, String title,double min, double max,Pageable pageable);
    Page<ProductDescription> findAllByTitleIsContainingAndMinPriceGreaterThanEqualAndMaxPriceLessThanEqual(String title,double min, double max,Pageable pageable);
    Page<ProductDescription> findAllByMinPriceGreaterThanEqualAndMaxPriceLessThanEqual(double min, double max, Pageable pageable);

//    @Query("SELECT pd From ProductDescription pd where pd.category.category = ?1 AND ...")
//    List<Object[]> findProductsInAllStoresBySKU();

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
