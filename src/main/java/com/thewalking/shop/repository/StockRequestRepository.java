package com.thewalking.shop.repository;

import com.thewalking.shop.entity.StockRequest;
import org.springframework.data.repository.CrudRepository;

public interface StockRequestRepository extends CrudRepository<StockRequest,Long> {
}
