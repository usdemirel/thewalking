package com.thewalking.shop.repository;

import com.thewalking.shop.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer,Long> {
}
