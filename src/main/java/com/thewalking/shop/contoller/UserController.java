package com.thewalking.shop.contoller;

import com.thewalking.shop.entity.User;
import com.thewalking.shop.dto.UserDto;
import com.thewalking.shop.model.Roles;
import com.thewalking.shop.service.UserService;
import org.springframework.dao.DataIntegrityViolationException;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.Valid;
import java.util.*;
import static com.thewalking.shop.exception.ErrorMessages.RECORD_ALREADY_EXISTS;

@CrossOrigin(origins = "*", maxAge = 36000)
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    //@Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PreAuthorize("hasAnyRole('ADMIN','OWNER')")
    @RequestMapping(value="/users", method = RequestMethod.GET)
    public List<User> listUser(){
        return userService.findAll();
    }

    //@Secured("ROLE_USER")
    //@PreAuthorize("hasRole('USER')")
    @PreAuthorize("hasAnyRole('OWNER')")
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public User getOne(@PathVariable(value = "id") Long id){
        return userService.findById(id);
    }


//    toggleUserActivenessById
    @PreAuthorize("hasAnyRole('OWNER')")
    @RequestMapping(value = "/users/{id}/active/toggle", method = RequestMethod.GET)
    public User toggleActiveness(@PathVariable Long id){
        return userService.toggleUserActivenessById(id);
    }

    //    changeUserRole
    @PreAuthorize("hasAnyRole('OWNER','MANAGER')")
    @RequestMapping(value = "/users/{id}/role/change", method = RequestMethod.POST)
    public User changeRole(@PathVariable Long id, @RequestBody String role){
        System.out.println(id + " -- " + role);
        return userService.changeUserRole(id, role);
    }

    @RequestMapping(value="/signup", method = RequestMethod.POST)
    public ResponseEntity<User> saveUser(@Valid @RequestBody UserDto user){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
        }catch (DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, RECORD_ALREADY_EXISTS.name(), e);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
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
/**
 * https://restfulapi.net/resource-naming/
 * http://api.example.com/cart-management/users/{id}/cart/checkout
 * http://api.example.com/song-management/users/{id}/playlist/play
 *
 * http://api.example.com/inventory-management/managed-entities/{id}/install-script-location
 *
 * HTTP GET http://api.example.com/device-management/managed-devices  //Get all devices
 * HTTP POST http://api.example.com/device-management/managed-devices  //Create new Device
 * HTTP GET http://api.example.com/device-management/managed-devices/{id}  //Get device for given Id
 * HTTP PUT http://api.example.com/device-management/managed-devices/{id}  //Update device for given Id
 * HTTP DELETE http://api.example.com/device-management/managed-devices/{id}  //Delete device for given Id
 *
 *Use query component to filter URI collection
 *http://api.example.com/device-management/managed-devices
 * http://api.example.com/device-management/managed-devices?region=USA
 * http://api.example.com/device-management/managed-devices?region=USA&brand=XYZ
 * http://api.example.com/device-management/managed-devices?region=USA&brand=XYZ&sort=installation-date
 *
 *
 */


}
