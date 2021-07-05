package com.thewalking.shop.service;

import com.thewalking.shop.entity.Employee;
import com.thewalking.shop.entity.User;

import java.util.List;

public interface EmployeeService {
    Employee save(Employee employee);

    List<Employee> findAll();

    Employee findById(Long id);

    Employee changeUserRole(Long id, String role);

    Employee toggleUserActivenessById(Long id);
}
