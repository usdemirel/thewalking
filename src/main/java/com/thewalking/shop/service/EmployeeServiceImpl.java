package com.thewalking.shop.service;

import com.thewalking.shop.dto.EmployeeDto;
import com.thewalking.shop.entity.Employee;
import com.thewalking.shop.exception.ErrorMessages;
import com.thewalking.shop.exception.UserException;
import com.thewalking.shop.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;

@Service(value = "employeeService")
@Slf4j
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Override
    public Employee save(EmployeeDto employee) {

        Employee newEmployee = new Employee();
        newEmployee.setEmail(employee.getEmail());
        newEmployee.setPassword(bcryptEncoder.encode(employee.getPassword()));
        newEmployee.setRole(employee.getRole());
        newEmployee.setFirstName(employee.getFirstName());
        newEmployee.setLastName(employee.getLastName());
        newEmployee.setPhone(employee.getPhone());
        newEmployee.setActive(employee.isActive());
        newEmployee.setAddress(employee.getAddress());
        newEmployee.setBranch(employee.getBranch());

        final String requesterRole = SecurityContextHolder.getContext().getAuthentication().getAuthorities().iterator().next().getAuthority();
        System.out.println("--"+SecurityContextHolder.getContext());
        if(requesterRole.equals("ROLE_OWNER")){
            log.info("user is owner");
        }else if(requesterRole.equals("ROLE_MANAGER")){
            log.debug("user is a manager");
            if(!Arrays.asList("HIREREQ","FIREREQ").contains(employee.getRole())
            || employee.getBranch().getId() != 5)
                throw new UserException(ErrorMessages.NO_AUTHORIZATION.getErrorMessage());
        }else{
            log.debug("user is neither owner nor manager");
            throw new UserException(ErrorMessages.NO_AUTHORIZATION.getErrorMessage());
        }
        employee.setPassword(bcryptEncoder.encode(employee.getPassword()));
        System.out.println(employee.getPassword() + "<");
        return employeeRepository.save(newEmployee);
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> list = new ArrayList<>();
        Iterator<Employee> it = employeeRepository.findAll().iterator();
        it.forEachRemaining( each -> list.add(each));
        return list;
    }

    @Override
    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElseThrow(
                new UserException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage()).get()
        );
    }

    @Override
    public Employee changeUserRole(Long id, String role) {
        return null;
    }

    @Override
    public Employee toggleUserActivenessById(Long id) {
        return null;
    }

    @Override
    public List<Employee> findEmployeesByBranchIdAndActiveIsTrue(Long branchId) {
        List<Employee> list = new ArrayList<>();
        employeeRepository.findAllByBranchId(branchId)
                .iterator().forEachRemaining(each -> {if(each.isActive()) list.add(each);});
        return list;
    }


}
