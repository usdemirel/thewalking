package com.thewalking.shop.contoller;

import com.thewalking.shop.dto.EmployeeDto;
import com.thewalking.shop.entity.Employee;
import com.thewalking.shop.entity.User;
import com.thewalking.shop.exception.UserException;
import com.thewalking.shop.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.thewalking.shop.exception.ErrorMessages.RECORD_ALREADY_EXISTS;

//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PreAuthorize("hasAnyRole('OWNER','MANAGER')")
    @RequestMapping(value="", method = RequestMethod.GET)
    public List<Employee> listUser(){
        return employeeService.findAll();
    }

    @PreAuthorize("hasAnyRole('OWNER','MANAGER')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Employee> getOne(@PathVariable(value = "id") Long id){
        System.out.println("emp called " + id);
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


    @PreAuthorize("hasAnyRole('MANAGER','OWNER')")
    @RequestMapping(value="", method = RequestMethod.POST)
    public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody EmployeeDto employee){
            try {
                return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.save(employee));
            }catch (DataIntegrityViolationException e){
                throw new ResponseStatusException(HttpStatus.CONFLICT, RECORD_ALREADY_EXISTS.name(), e);
            }catch (Exception e){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
            }
    }

    @PreAuthorize("hasAnyRole('OWNER','MANAGER','EMPLOYEE')")
    @RequestMapping(value="", method = RequestMethod.PUT)
    public ResponseEntity<User> update(@Valid @RequestBody Employee employee){
        System.out.println(employee);
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.update(employee));
        }catch (DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, RECORD_ALREADY_EXISTS.name(), e);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @PreAuthorize("hasAnyRole('OWNER','MANAGER')")
    @RequestMapping(value = "/branches/{branchId}", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> findEmployeesByBranchIdAndActiveIsTrue(@PathVariable Long branchId){
            try{
                return ResponseEntity.status(HttpStatus.OK).body(employeeService.findEmployeesByBranchIdAndActiveIsTrue(branchId));
            }catch (Exception e){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage(),e);
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

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ResponseStatusException.class)
    public Map<String, String> handleResponseStatusException(ResponseStatusException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put(ex.getStatus().toString(),ex.getReason());
        return errors;
    }
}
