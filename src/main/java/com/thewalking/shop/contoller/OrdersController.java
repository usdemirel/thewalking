package com.thewalking.shop.contoller;

import com.thewalking.shop.entity.Orders;
import com.thewalking.shop.service.OrderItemsService;
import com.thewalking.shop.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrdersController {

    @Autowired
    OrdersService ordersService;

    @Autowired
    OrderItemsService orderItemsService;

    @RequestMapping(value="/orders", method= RequestMethod.POST)
    public ResponseEntity<Orders> save(@RequestBody Orders orders){
        System.out.println(orders);
        return ResponseEntity.status(HttpStatus.CREATED).body(ordersService.save(orders));
    }

    @RequestMapping(value="/orders", method= RequestMethod.GET)
    public List<Orders> findAll(){
        return ordersService.findAll();
    }
}
