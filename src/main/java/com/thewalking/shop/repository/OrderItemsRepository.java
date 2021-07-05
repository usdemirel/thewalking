package com.thewalking.shop.repository;

import com.thewalking.shop.entity.OrderItems;
import org.springframework.data.repository.CrudRepository;

public interface OrderItemsRepository extends CrudRepository<OrderItems,Long> {
}
