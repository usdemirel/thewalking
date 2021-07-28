package com.thewalking.shop.service;

import com.thewalking.shop.entity.Customer;
import com.thewalking.shop.entity.Orders;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrdersService {
    Orders save(Orders orders);
    Orders checkoutAllItemsInCart(Customer customer);
    List<Orders> findAll();
    Optional<Orders> findById(Long id);
    void deleteById(Long id);
    List<Orders> findAllByOrderDate(LocalDate date);

}
