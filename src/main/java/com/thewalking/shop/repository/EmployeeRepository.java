package com.thewalking.shop.repository;

import com.thewalking.shop.entity.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends CrudRepository<Employee,Long> {

    Iterable<Employee> findAllByBranchId(Long branchId);

}
