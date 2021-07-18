package com.thewalking.shop.repository;

import com.thewalking.shop.entity.Orders;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
@Repository
public interface OrdersRepository extends CrudRepository<Orders,Long> {


}
