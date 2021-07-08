package com.thewalking.shop.service;

import com.thewalking.shop.dto.UserDto;
import com.thewalking.shop.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Customer save(UserDto customer);
    Optional<Customer> findById(Long id);
    List<Customer> findAll();
    void deleteById(Long id);
}
