package com.thewalking.shop.service;

import com.thewalking.shop.entity.StockRequest;

import java.util.List;


public interface StockRequestService {

    List<StockRequest> findAll();
    StockRequest findById(Long id);
    StockRequest save(StockRequest stockRequest);
    void deleteById(Long id);
    List<StockRequest> findAllByManagerId(Long id);
    List<StockRequest> findAllByStatus(boolean status);
    List<StockRequest> findAllByManagerIdAndStatus(Long id, boolean status);
    List<StockRequest> findAllByStockId(Long id);


}
