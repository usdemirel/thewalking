package com.thewalking.shop.service;

import com.thewalking.shop.entity.OrderItems;
import java.util.List;
import java.util.Optional;

public interface OrderItemsService {
    OrderItems save(OrderItems orderItems);
    List<OrderItems> findAll();
    Optional<OrderItems> findById(Long id);
    void delete(Long id);
    List<OrderItems> findAllByCustomerIdAndOrderIsNull(Long customerId);
}
