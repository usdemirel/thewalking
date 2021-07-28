package com.thewalking.shop.contoller;

import com.thewalking.shop.entity.Customer;
import com.thewalking.shop.entity.Orders;
import com.thewalking.shop.service.CustomerService;
import com.thewalking.shop.service.OrderItemsService;
import com.thewalking.shop.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@RequestMapping("/api/orders")
@RestController
public class OrdersController {

    @Autowired
    OrdersService ordersService;

    @Autowired
    OrderItemsService orderItemsService;

    @Autowired
    CustomerService customerService;

    @Transactional
    @RequestMapping(value="", method= RequestMethod.POST)
    public ResponseEntity<Orders> handleOrderRequest(@RequestBody Customer customer){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(ordersService.checkoutAllItemsInCart(customer));
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "Transaction FAILED",e);
        }
    }

    @PreAuthorize("hasAnyRole('OWNER')")
    @RequestMapping(value="", method= RequestMethod.GET)
    public ResponseEntity<List<Orders>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(ordersService.findAll());
    }

    @RequestMapping(value="/{date}", method= RequestMethod.GET)
    public ResponseEntity<List<Orders>> findAllByOrderDate(@PathVariable String date){
        return ResponseEntity.status(HttpStatus.OK).body(ordersService.findAllByOrderDate(LocalDate.parse(date)));
    }
}
