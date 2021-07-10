package com.thewalking.shop.contoller;

import com.thewalking.shop.entity.Stock;
import com.thewalking.shop.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StockController {
    @Autowired
    StockService stockService;

    @RequestMapping(value="/stocks", method= RequestMethod.POST)
    public ResponseEntity<Stock> save(@RequestBody Stock stock){
        System.out.println(stock);
        return ResponseEntity.status(HttpStatus.CREATED).body(stockService.save(stock));
    }

    @RequestMapping(value="/stocks", method= RequestMethod.GET)
    public List<Stock> findALl(){
        return stockService.findAll();
    }








}
