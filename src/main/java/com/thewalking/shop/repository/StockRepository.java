package com.thewalking.shop.repository;

import com.thewalking.shop.dto.ProductsTotal;
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
    @Query("update Stock stock set stock.price = ?1 where stock.Id = ?2")
    int setPriceForStock(Double price, Long Id);

    @Modifying
    @Query("update Stock stock set stock.quantity = ?1 where stock.Id = ?2")
    int setQuantityForStock(Integer quantity, Long Id);

    @Modifying
    @Query("update Stock stock set stock.salesPrice = ?1, stock.salesStartDate = ?2, stock.salesEndDate = ?3 where stock.Id = ?4")
    int setSalesPriceAndSalesStartDateAndSalesEndDateForStock(Double salesPrice, LocalDate salesStartDate, LocalDate salesEndDate, Long Id);

    @Query("SELECT sum(stock.quantity) as total, product.SKU From Stock stock inner Join Product product on stock.product.id = product.id group by product.id")
    List<ProductsTotal> findProductsInAllStoresBySKU();


}
