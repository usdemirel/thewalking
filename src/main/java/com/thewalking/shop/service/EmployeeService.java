package com.thewalking.shop.service;

import com.thewalking.shop.dto.EmployeeDto;
import com.thewalking.shop.entity.Employee;
import com.thewalking.shop.entity.User;

import java.util.List;

public interface EmployeeService {
    Employee save(EmployeeDto employee);
    Employee update(Employee employee);

    List<Employee> findAll();

    Employee findById(Long id);

    Employee toggleUserActivenessById(Long id);

    List<Employee> findEmployeesByBranchIdAndActiveIsTrue(Long branchId);

    boolean hasAllRoles(String... roles);

}
