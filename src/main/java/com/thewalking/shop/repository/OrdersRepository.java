package com.thewalking.shop.repository;

import com.thewalking.shop.entity.Orders;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends CrudRepository<Orders,Long> {


}
