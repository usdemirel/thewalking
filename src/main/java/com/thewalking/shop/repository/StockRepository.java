package com.thewalking.shop.repository;

import com.thewalking.shop.entity.ProductDescription;
import com.thewalking.shop.entity.Stock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.time.LocalDate;
import java.util.List;

@CrossOrigin("http://localhost:4200")
public interface StockRepository extends CrudRepository<Stock,Long> {

    @Modifying
    @Query("update Stock stock set stock.price = ?1 where stock.id = ?2")
    int setPriceForStock(Double price, Long Id);

    @Modifying
    @Query("update Stock stock set stock.quantity = ?1 where stock.id = ?2")
    int setQuantityForStock(Integer quantity, Long Id);

    @Modifying
    @Query("update Stock stock set stock.salesPrice = ?1, stock.salesStartDate = ?2, stock.salesEndDate = ?3 where stock.id = ?4")
    int setSalesPriceAndSalesStartDateAndSalesEndDateForStock(Double salesPrice, LocalDate salesStartDate, LocalDate salesEndDate, Long Id);

    List<Stock> findStocksByBarcode(String barcode);
    List<Stock> findStocksByProductId(Long productId);
    List<Stock> findStocksByProduct_SKU(String SKU);
    List<Stock> findStocksByQuantityBetween(int min, int max);
    List<Stock> findStocksByPriceBetween(double minPrice, double maxPrice);
    List<Stock> findStocksBySalesStartDateBeforeAndSalesEndDateIsAfter(LocalDate todayPlusOne, LocalDate todayMinusOne);

    @Query("SELECT sum(stock.quantity) as total, product.SKU From Stock stock inner Join Product product on stock.product.id = product.id group by product.SKU")
    List<Object[]> findProductsInAllStoresBySKU();

    @Query("SELECT sum(stock.quantity) as total, product.id, product.productDescription.id,product.SKU, product.Size, " +
            "product.productDescription.title,product.productDescription.brand,product.productDescription.image, product.productDescription.rating " +
            "From Stock stock inner Join Product product " +
            "on stock.product.id = product.id " +
            "where product.productDescription.isActive = true " +
            "group by product.id, product.productDescription.id, product.SKU, product.Size, product.productDescription.title,product.productDescription.brand,product.productDescription.image,product.productDescription.rating")
    List<Object[]> findProductsInAllStoresByTitleBrandImageRatingSKUSize();


    @Query("SELECT sum(stock.quantity) as total, product.id, product.productDescription.id,product.SKU, product.Size, " +
            "product.productDescription.title,product.productDescription.brand,product.productDescription.image, product.productDescription.rating " +
            "From Stock stock inner Join Product product " +
            "on stock.product.id = product.id " +
            "where product.productDescription.isActive = true and stock.branch.id = ?1 " +
            "group by product.id, product.productDescription.id, product.SKU, product.Size, product.productDescription.title,product.productDescription.brand,product.productDescription.image,product.productDescription.rating")
    List<Object[]> findProductsInAllStoresByTitleBrandImageRatingSKUSizeByBranch(Long branchId);


    List<Stock> findAllByProductProductDescriptionId(Long id);




}
