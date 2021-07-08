package com.thewalking.shop.repository;

import com.thewalking.shop.entity.OrderItems;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemsRepository extends CrudRepository<OrderItems,Long> {
    Iterable<OrderItems> findAllByCustomerIdAndOrderIsNull(Long customerId);
}
