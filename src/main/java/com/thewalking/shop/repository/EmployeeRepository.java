package com.thewalking.shop.repository;

import com.thewalking.shop.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:4200")
public interface EmployeeRepository extends CrudRepository<Employee,Long> {

    Iterable<Employee> findAllByBranchId(Long branchId);

}
