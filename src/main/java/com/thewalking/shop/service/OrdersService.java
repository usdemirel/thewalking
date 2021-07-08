package com.thewalking.shop.service;

import com.thewalking.shop.entity.Orders;

import java.util.List;
import java.util.Optional;

public interface OrdersService {
    Orders save(Orders orders);
    List<Orders> findAll();
    Optional<Orders> findById(Long id);
    void deleteById(Long id);
}
