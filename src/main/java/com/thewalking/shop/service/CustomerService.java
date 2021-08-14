package com.thewalking.shop.service;

import com.thewalking.shop.dto.UserDto;
import com.thewalking.shop.entity.Customer;
import com.thewalking.shop.entity.User;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Customer save(UserDto customer);
    Customer save(Customer customer);
    Customer update(Customer customer) throws Exception;
    Optional<Customer> findById(Long id);
    List<Customer> findAll();
    void deleteById(Long id);
    Optional<Customer> findByEmail(String email);

}
