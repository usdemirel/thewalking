package com.thewalking.shop.repository;

import com.thewalking.shop.entity.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee,Long> {


}
