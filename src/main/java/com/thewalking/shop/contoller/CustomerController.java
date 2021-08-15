package com.thewalking.shop.contoller;

import com.thewalking.shop.dto.UserDto;
import com.thewalking.shop.entity.Address;
import com.thewalking.shop.entity.Customer;
import com.thewalking.shop.entity.User;
import com.thewalking.shop.exception.ErrorMessages;
import com.thewalking.shop.exception.UserException;
import com.thewalking.shop.service.CustomerService;
import com.thewalking.shop.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.thewalking.shop.exception.ErrorMessages.RECORD_ALREADY_EXISTS;

@CrossOrigin
@RequestMapping("/api/customers")
@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @Autowired
    UserService userService;

    @RequestMapping(value="/signup", method = RequestMethod.POST)
    public ResponseEntity<User> saveCustomer(@Valid @RequestBody UserDto user){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(customerService.save(user));
        }catch (DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, RECORD_ALREADY_EXISTS.toString());
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }


    @RequestMapping(value="/address", method = RequestMethod.POST)
    public ResponseEntity<User> updateOrCreateCustomerAddress(@Valid @RequestBody Address address){
        System.out.println("___________________________--------------");
        System.out.println(address);
        try {
            Customer customer = customerService.findByEmail(userService.getUserEmail()).get();
            customer.setAddress(address);
            customerService.save(customer);
            return ResponseEntity.status(HttpStatus.CREATED).body(customer);
        }catch (DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, RECORD_ALREADY_EXISTS.toString());
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @PreAuthorize("hasAnyRole('CUSTOMER')")
    @RequestMapping(value="", method = RequestMethod.PUT)
    public ResponseEntity<User> update(@Valid @RequestBody Customer customer){
        final String email = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(email);
        if(!customer.getEmail().equals(email)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorMessages.NO_AUTHORIZATION.name());
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(customerService.update(customer));
        }catch (DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, RECORD_ALREADY_EXISTS.name(), e);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @RequestMapping(value="", method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findAll());
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<Customer> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.findById(id).orElseThrow(
                () -> new UserException(ErrorMessages.NO_RECORD_FOUND.name())
        ));
    }

    @SneakyThrows
    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable Long id){
        try{
            customerService.deleteById(id);
        }catch(Exception exception){
            throw new Exception(ErrorMessages.COULD_NOT_DELETE_RECORD.name());
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Map<String, String> handleValidationExceptions(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getConstraintViolations().stream().forEach(cv ->
                errors.put(cv.getPropertyPath().toString(), cv.getMessage()));

        return errors;
    }

//    @ResponseStatus(HttpStatus.CONFLICT)
//    @ExceptionHandler(ResponseStatusException.class)
//    public Map<String, String> handleResponseStatusException(ResponseStatusException ex) {
//        Map<String, String> errors = new HashMap<>();
//        errors.put(ex.getStatus().toString(),ex.getReason());
//        return errors;
//    }


}
