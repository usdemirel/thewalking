package com.thewalking.shop.service;

import com.thewalking.shop.dto.ProductStoreTotalReportDto;
import com.thewalking.shop.entity.ProductDescription;
import com.thewalking.shop.entity.Stock;
import com.thewalking.shop.exception.ErrorMessages;
import com.thewalking.shop.exception.UserException;
import com.thewalking.shop.model.StockDetails;
import com.thewalking.shop.model.StockDto;
import com.thewalking.shop.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;

@Service
public class StockServiceImpl implements StockService{

    @Autowired
    StockRepository stockRepository;

    @Autowired
    ProductDescriptionService productDescriptionService;

    @Autowired
    EmployeeService employeeService;

    @Override
    public Optional<Stock> findById(Long aLong) {
        return stockRepository.findById(aLong);
    }

    @Transactional
    @Override
    public Stock save(Stock stock) {
        Long pdId = stock.getProduct().getProductDescription().getId();
        ProductDescription productDescription = productDescriptionService.findById(pdId).get();
        double minPrice = productDescription.getMinPrice();
        double maxPrice = productDescription.getMaxPrice();

        if(minPrice==0 && maxPrice == 0){
            productDescriptionService.setMinPriceAndMaxPriceForProductDescription(stock.getPrice(), stock.getPrice(), pdId);
        }else if(stock.getPrice()<minPrice){
            productDescriptionService.setMinPriceForProductDescription(stock.getPrice(), pdId);
        }else if(stock.getPrice()>maxPrice){
            productDescriptionService.setMaxPriceForProductDescription(stock.getPrice(), pdId);
        }
        return stockRepository.save(stock);
    }

    @Override
    public List<Stock> findAll() {
        List<Stock> list = new ArrayList<>();
        stockRepository.findAll().iterator().forEachRemaining(each -> list.add(each));
        return list;
    }

    @Override
    public void deleteById(Long aLong) {
        stockRepository.deleteById(aLong);
    }

    @Override
    public int setPriceForStock(Double price, Long Id) {
        return stockRepository.setPriceForStock(price,Id);
    }

    @Override
    public int addQuantityForStock(Integer quantity, Long Id) {
        Stock currentStock = stockRepository.findById(Id).get();
        //it is better to create a table to keep entries.
        stockRepository.setQuantityForStock(currentStock.getQuantity()+quantity,Id);
        return 0;
    }

    @Override
    public int setSalesPriceAndSalesStartDateAndSalesEndDateForStock(Double salesPrice, LocalDate salesStartDate, LocalDate salesEndDate, Long Id) {
        return stockRepository.setSalesPriceAndSalesStartDateAndSalesEndDateForStock(salesPrice, salesStartDate, salesEndDate, Id);
    }

    @Override
    public List<Object[]> findProductsInAllStoresBySKU() {
        List<Object[]> al = stockRepository.findProductsInAllStoresBySKU();

        for( Object[] obj : al){
            System.out.println(obj[0] + " ");
            System.out.print(obj[1]);
        }

        return al;
    }

    @Override
    public List<ProductStoreTotalReportDto> findProductsInAllStoresByTitleBrandImageRatingSKUSize() {
        return convertToListOfProductStoreTotalReportDto(stockRepository.findProductsInAllStoresByTitleBrandImageRatingSKUSize());
    }

    @Override
    public List<ProductStoreTotalReportDto> findProductsInAllStoresByTitleBrandImageRatingSKUSizeByBranch(Long branchId) {
        if((employeeService.hasAllRoles("MANAGER",branchId.toString()) || employeeService.hasAllRoles("OWNER")))
            return convertToListOfProductStoreTotalReportDto(stockRepository.findProductsInAllStoresByTitleBrandImageRatingSKUSizeByBranch(branchId));
        else
            throw new UserException(ErrorMessages.NO_AUTHORIZATION.getErrorMessage());
    }

    @Override
    public List<Stock> findStocksByBarcode(String barcode) {
        return stockRepository.findStocksByBarcode(barcode);
    }

    @Override
    public List<Stock> findStocksByProductId(Long productId) {
        return stockRepository.findStocksByProductId(productId);
    }

    @Override
    public List<Stock> findStocksByProduct_SKU(String SKU) {
        return stockRepository.findStocksByProduct_SKU(SKU);
    }

    @Override
    public List<Stock> findStocksByQuantityBetween(int min, int max) {
        return stockRepository.findStocksByQuantityBetween(min, max);
    }

    @Override
    public List<Stock> findStocksByPriceBetween(double minPrice, double maxPrice) {
        return stockRepository.findStocksByPriceBetween(minPrice, maxPrice);
    }

    @Override
    public List<Stock> findStocksBySalesStartDateBeforeAndSalesEndDateIsAfter(LocalDate today, LocalDate today2) {
        return stockRepository.findStocksBySalesStartDateBeforeAndSalesEndDateIsAfter(today.plusDays(1),today2.minusDays(1));
    }

    @Override
    public List<Stock> findAllByProductProductDescriptionId(Long id) {
        return stockRepository.findAllByProductProductDescriptionId(id);
    }

    @Override
    public StockDto findDistinctProductsByProductDescriptionId(Long id) {
        List<Stock> stockList = stockRepository.findAllByProductProductDescriptionId(id);
        StockDto stockDto = new StockDto();
        stockDto.setStockDetailsList(new ArrayList<>());
        stockDto.setProductDescription(stockList.get(0).getProduct().getProductDescription());
        Set<String> sizes = new HashSet<>();
        for(Stock stock : stockList){
            if(!sizes.contains(stock.getProduct().getSize()) && stock.getQuantity() != 0){
                sizes.add(stock.getProduct().getSize());
                StockDetails stockDetails = new StockDetails(
                        stock.getId(),
                        stock.getPrice(),
                        stock.getQuantity(),
                        stock.getBranch(),
                        stock.getProduct().getSize(),
                        stock.getProduct().getMaxOrderQuantity()
                );
                stockDto.getStockDetailsList().add(stockDetails);
            }
        }
        return stockDto;
    }

    private List<ProductStoreTotalReportDto> convertToListOfProductStoreTotalReportDto(List<Object[]> al){
        List<ProductStoreTotalReportDto> list = new ArrayList<>();
        for( Object[] obj : al){
            list.add(new ProductStoreTotalReportDto(Integer.valueOf(Math.toIntExact((Long) obj[0])),(Long) obj[1],(Long) obj[2],
                    (String) obj[3],(String) obj[4],(String) obj[5],(String) obj[6],(String) obj[7],(Double) obj[8]));
        }
        return list;
    }

}
