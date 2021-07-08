package com.thewalking.shop.contoller;

import com.thewalking.shop.dto.UserDto;
import com.thewalking.shop.entity.Customer;
import com.thewalking.shop.entity.User;
import com.thewalking.shop.exception.ErrorMessages;
import com.thewalking.shop.exception.UserException;
import com.thewalking.shop.service.CustomerService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

import static com.thewalking.shop.exception.ErrorMessages.RECORD_ALREADY_EXISTS;

@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @RequestMapping(value="/customers/signup", method = RequestMethod.POST)
    public ResponseEntity<User> saveUser(@Valid @RequestBody UserDto user){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(customerService.save(user));
        }catch (DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, RECORD_ALREADY_EXISTS.name(), e);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @RequestMapping(value="/customers", method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findAll());
    }

    @RequestMapping(value="/customers/{id}", method = RequestMethod.GET)
    public ResponseEntity<Customer> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.findById(id).orElseThrow(
                () -> new UserException(ErrorMessages.NO_RECORD_FOUND.name())
        ));
    }

    @SneakyThrows
    @RequestMapping(value="/customers/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable Long id){
        try{
            customerService.deleteById(id);
        }catch(Exception exception){
            throw new Exception(ErrorMessages.COULD_NOT_DELETE_RECORD.name());
        }
    }




}
