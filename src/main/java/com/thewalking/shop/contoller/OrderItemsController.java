package com.thewalking.shop.contoller;

import com.thewalking.shop.dto.UserDto;
import com.thewalking.shop.entity.Customer;
import com.thewalking.shop.entity.OrderItems;
import com.thewalking.shop.entity.User;
import com.thewalking.shop.service.OrderItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

import java.util.List;

import static com.thewalking.shop.exception.ErrorMessages.RECORD_ALREADY_EXISTS;

@RestController
public class OrderItemsController {

    @Autowired
    OrderItemsService orderItemsService;

    @RequestMapping(value="/orderitems", method = RequestMethod.POST)
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

    @RequestMapping(value="/orderitems", method = RequestMethod.GET)
    public ResponseEntity<List<OrderItems>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(orderItemsService.findAll());
    }

    @RequestMapping(value="/orderitems/user", method = RequestMethod.POST)
    public ResponseEntity<List<OrderItems>> findByUserId(@RequestBody Customer customer){
        List<OrderItems> list = orderItemsService.findAllByCustomerIdAndOrderIsNull(customer.getId());
//        System.out.println(list);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }





}
