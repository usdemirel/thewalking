package com.thewalking.shop.contoller;

import com.thewalking.shop.entity.Customer;
import com.thewalking.shop.entity.OrderItems;
import com.thewalking.shop.service.OrderItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/orderitems")
@RestController
public class OrderItemsController {

    @Autowired
    OrderItemsService orderItemsService;

    @RequestMapping(value="", method = RequestMethod.POST)
    public ResponseEntity<OrderItems> save(@Valid @RequestBody OrderItems orderItems){
        System.out.println(orderItems);
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(orderItemsService.save(orderItems));
        }catch (DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "DataIntegrityViolationException", e);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @RequestMapping(value="", method = RequestMethod.GET)
    public ResponseEntity<List<OrderItems>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(orderItemsService.findAll());
    }

    @RequestMapping(value="/user", method = RequestMethod.POST)
    public ResponseEntity<List<OrderItems>> findByUser(@RequestBody Customer customer){
        List<OrderItems> list = orderItemsService.findAllByCustomerIdAndOrderIsNull(customer.getId());
//        System.out.println(list);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteById(@PathVariable Long id){
        orderItemsService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
