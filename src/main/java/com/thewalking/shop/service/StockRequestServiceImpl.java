package com.thewalking.shop.service;

import com.thewalking.shop.entity.StockRequest;
import com.thewalking.shop.exception.StockException;
import com.thewalking.shop.repository.StockRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StockRequestServiceImpl implements StockRequestService{

    @Autowired
    StockRequestRepository stockRequestRepository;


    @Override
    public List<StockRequest> findAll() {
        return (List<StockRequest>) stockRequestRepository.findAll();
    }

    @Override
    public StockRequest findById(Long id) {
        return stockRequestRepository.findById(id).orElseThrow(
                new StockException("You request could not be handled").get()
        );
    }

    @Override
    public StockRequest save(StockRequest stockRequest) {
        return stockRequestRepository.save(stockRequest);
    }

    @Override
    public void deleteById(Long id) {
        stockRequestRepository.deleteById(id);
    }

    @Override
    public List<StockRequest> findAllByManagerId(Long id) {
        return stockRequestRepository.findAllByManagerId(id);
    }

    @Override
    public List<StockRequest> findAllByStatus(boolean status) {
        return stockRequestRepository.findAllByStatus(status);
    }

    @Override
    public List<StockRequest> findAllByManagerIdAndStatus(Long id, boolean status) {
        return stockRequestRepository.findAllByManagerIdAndStatus(id,status);
    }

    @Override
    public List<StockRequest> findAllByStockId(Long id) {
        return stockRequestRepository.findAllByStockId(id);
    }


}