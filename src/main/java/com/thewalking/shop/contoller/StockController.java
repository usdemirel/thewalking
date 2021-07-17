package com.thewalking.shop.contoller;

import com.thewalking.shop.dto.StockDiscountDto;
import com.thewalking.shop.dto.StockPriceQuantityDto;
import com.thewalking.shop.entity.Stock;
import com.thewalking.shop.exception.ErrorMessages;
import com.thewalking.shop.exception.StockException;
import com.thewalking.shop.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RequestMapping("/api/stocks")
@RestController
public class StockController {
    @Autowired
    StockService stockService;

    @RequestMapping(value="", method= RequestMethod.POST)
    public ResponseEntity<Stock> save(@RequestBody Stock stock){
        System.out.println(stock);
        return ResponseEntity.status(HttpStatus.CREATED).body(stockService.save(stock));
    }

    @RequestMapping(value="", method= RequestMethod.GET)
    public List<Stock> findALl(){
        stockService.findProductsInAllStoresBySKU();
        return stockService.findAll();
    }

    @PreAuthorize("hasAnyRole('MANAGER','OWNER')")
    @RequestMapping(value="/price", method= RequestMethod.PUT)
    public ResponseEntity<Stock> setPriceForStock(@RequestBody StockPriceQuantityDto spqDto) {
        try {
            stockService.setPriceForStock(spqDto.getPrice(), spqDto.getId());
            Stock stock = stockService.findById(spqDto.getId()).get();
            return ResponseEntity.status(HttpStatus.OK).body(stock);
        } catch (Exception ex){
            throw new StockException(ErrorMessages.COULD_NOT_SAVE_RECORD.getErrorMessage(), ex.getCause());
        }
    }

    @PreAuthorize("hasAnyRole('MANAGER','OWNER')")
    @RequestMapping(value="/quantity", method= RequestMethod.PUT)
    public ResponseEntity<Stock> addQuantityForStock(@RequestBody StockPriceQuantityDto spqDto) {
        try {
            stockService.addQuantityForStock(spqDto.getQuantity(), spqDto.getId());
            Stock stock = stockService.findById(spqDto.getId()).get();
            return ResponseEntity.status(HttpStatus.OK).body(stock);
        } catch (Exception ex){
            throw new StockException(ErrorMessages.COULD_NOT_SAVE_RECORD.getErrorMessage(), ex.getCause());
        }
    }

    @PreAuthorize("hasAnyRole('MANAGER','OWNER')")
    @RequestMapping(value="/discount", method= RequestMethod.PUT)
    public ResponseEntity<Stock> setSalesPriceAndSalesStartDateAndSalesEndDateForStock(StockDiscountDto discountDto) {
        try {
            stockService.setSalesPriceAndSalesStartDateAndSalesEndDateForStock(discountDto.getSalesPrice(),
                    discountDto.getSalesStartDate(),discountDto.getSalesEndDate(),discountDto.getId());
            Stock stock = stockService.findById(discountDto.getId()).get();
            return ResponseEntity.status(HttpStatus.OK).body(stock);
        } catch (Exception ex){
            throw new StockException(ErrorMessages.COULD_NOT_SAVE_RECORD.getErrorMessage(), ex.getCause());
        }
    }




}
