package com.thewalking.shop.contoller;

import com.thewalking.shop.dto.EmployeeDto;
import com.thewalking.shop.entity.Employee;
import com.thewalking.shop.exception.UserException;
import com.thewalking.shop.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import static com.thewalking.shop.exception.ErrorMessages.RECORD_ALREADY_EXISTS;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PreAuthorize("hasAnyRole('OWNER','MANAGER')")
    @RequestMapping(value="/", method = RequestMethod.GET)
    public List<Employee> listUser(){
        return employeeService.findAll();
    }

    @PreAuthorize("hasAnyRole('OWNER','MANAGER')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Employee> getOne(@PathVariable(value = "id") Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(employeeService.findById(id));
        }catch (UserException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }
    }

//    toggleUserActivenessById
    @PreAuthorize("hasAnyRole('OWNER')")
    @RequestMapping(value = "/{id}/active/toggle", method = RequestMethod.GET)
    public Employee toggleActiveness(@PathVariable Long id){
        return employeeService.toggleUserActivenessById(id);
    }

    //    changeUserRole
    @PreAuthorize("hasAnyRole('OWNER','MANAGER')")
    @RequestMapping(value = "/{id}/role/change", method = RequestMethod.POST)
    public Employee changeRole(@PathVariable Long id, @RequestBody String role){
        System.out.println(id + " -- " + role);
        return employeeService.changeUserRole(id, role);
    }

    @PreAuthorize("hasAnyRole('MANAGER','OWNER')")
    @RequestMapping(value="/signup", method = RequestMethod.POST)
    public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody EmployeeDto employee){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.save(employee));
        }catch (DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, RECORD_ALREADY_EXISTS.name(), e);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @PreAuthorize("hasAnyRole('OWNER','MANAGER')")
    @RequestMapping(value = "/branches/{branchId}", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> findEmployeesByBranchIdAndActiveIsTrue(@PathVariable Long branchId){
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        List<String> auths = authorities.stream().map(each -> each.getAuthority()).collect(Collectors.toList());
        if((auths.contains("ROLE_MANAGER") && auths.contains("ROLE_" +branchId.toString()))
        || auths.contains("ROLE_OWNER")){
            try{
                return ResponseEntity.status(HttpStatus.OK).body(employeeService.findEmployeesByBranchIdAndActiveIsTrue(branchId));
            }catch (Exception e){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage(),e);
            }
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

/*
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Map<String, String> handleValidationExceptions(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getConstraintViolations().stream().forEach(cv ->
                 errors.put(cv.getPropertyPath().toString(), cv.getMessage()));

        return errors;
    }


 */

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
