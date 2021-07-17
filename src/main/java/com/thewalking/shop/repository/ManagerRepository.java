package com.thewalking.shop.repository;

import com.thewalking.shop.entity.Employee;
import com.thewalking.shop.entity.Manager;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin("http://localhost:4200")
public interface ManagerRepository extends CrudRepository<Manager,Long> {

}
