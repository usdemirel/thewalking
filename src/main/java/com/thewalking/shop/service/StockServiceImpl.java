package com.thewalking.shop.service;

import com.thewalking.shop.entity.Stock;
import com.thewalking.shop.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StockServiceImpl implements StockService{

    @Autowired
    StockRepository stockRepository;


    @Override
    public Optional<Stock> findById(Long aLong) {
        return stockRepository.findById(aLong);
    }

    @Override
    public Stock save(Stock stock) {
        return stockRepository.save(stock);
    }

    @Override
    public Iterable<Stock> findAll() {
        return stockRepository.findAll();
    }

    @Override
    public void deleteById(Long aLong) {
        stockRepository.deleteById(aLong);
    }
}
