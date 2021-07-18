package com.thewalking.shop.repository;

import com.thewalking.shop.entity.OrderItems;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
@Repository
public interface OrderItemsRepository extends CrudRepository<OrderItems,Long> {
    Iterable<OrderItems> findAllByCustomerIdAndOrderIsNull(Long customerId);
}
