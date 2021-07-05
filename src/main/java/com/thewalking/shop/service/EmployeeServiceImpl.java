package com.thewalking.shop.service;

import com.thewalking.shop.entity.Employee;
import com.thewalking.shop.entity.User;
import com.thewalking.shop.exception.ErrorMessages;
import com.thewalking.shop.exception.UserException;
import com.thewalking.shop.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Service(value = "employeeService")
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Override
    public Employee save(Employee employee) {

        if(!Arrays.asList("CUSTOMER","GUEST","NEWHIRE","FIRE").contains(employee.getRole()))
            throw new UserException(ErrorMessages.NO_AUTHORIZATION.getErrorMessage());
        employee.setPassword(bcryptEncoder.encode(employee.getPassword()));
        System.out.println(employee.getPassword() + "<");
        return employeeRepository.save(employee);
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
        return null;
    }

    @Override
    public Employee changeUserRole(Long id, String role) {
        return null;
    }

    @Override
    public Employee toggleUserActivenessById(Long id) {
        return null;
    }


}
