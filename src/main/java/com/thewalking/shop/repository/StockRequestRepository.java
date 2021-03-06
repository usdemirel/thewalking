package com.thewalking.shop.repository;

import com.thewalking.shop.entity.StockRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin("http://localhost:4200")
public interface StockRequestRepository extends CrudRepository<StockRequest,Long> {

    List<StockRequest> findAllByManagerId(Long id);
    List<StockRequest> findAllByStatus(boolean status);
    List<StockRequest> findAllByManagerIdAndStatus(Long id, boolean status);
    List<StockRequest> findAllByStockId(Long id);


}