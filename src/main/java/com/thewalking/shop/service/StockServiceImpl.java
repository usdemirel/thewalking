package com.thewalking.shop.service;

import com.thewalking.shop.entity.Stock;
import com.thewalking.shop.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
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
    public List<Stock> findAll() {
        List<Stock> list = new ArrayList<>();
        stockRepository.findAll().iterator().forEachRemaining(each -> list.add(each));
        return list;
    }

    @Override
    public void deleteById(Long aLong) {
        stockRepository.deleteById(aLong);
    }
}
