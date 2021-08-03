package com.thewalking.shop.service;

import com.thewalking.shop.dto.ProductStoreTotalReportDto;
import com.thewalking.shop.entity.Stock;
import com.thewalking.shop.model.StockDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface StockService {

    Optional<Stock> findById(Long aLong);

    Stock save(Stock stock);

    List<Stock> findAll();

    void deleteById(Long aLong);

    int setPriceForStock(Double price, Long Id);

    int addQuantityForStock(Integer quantity, Long Id);

    int setSalesPriceAndSalesStartDateAndSalesEndDateForStock(Double salesPrice, LocalDate salesStartDate, LocalDate salesEndDate, Long Id);

    List<Object[]> findProductsInAllStoresBySKU();
    List<ProductStoreTotalReportDto> findProductsInAllStoresByTitleBrandImageRatingSKUSize();
    List<ProductStoreTotalReportDto> findProductsInAllStoresByTitleBrandImageRatingSKUSizeByBranch(Long branchId);
    List<Stock> findStocksByBarcode(String barcode);
    List<Stock> findStocksByProductId(Long productId);
    List<Stock> findStocksByProduct_SKU(String SKU);
    List<Stock> findStocksByQuantityBetween(int min, int max);
    List<Stock> findStocksByPriceBetween(double minPrice, double maxPrice);
    List<Stock> findStocksBySalesStartDateBeforeAndSalesEndDateIsAfter(LocalDate dayAfter, LocalDate dayBefore);
    StockDto findDistinctProductsByProductDescriptionId(Long id);
    List<Stock> findAllByProductProductDescriptionId(Long id);

}
