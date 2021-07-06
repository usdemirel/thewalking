package com.thewalking.shop.repository;

import com.thewalking.shop.entity.Employee;
import com.thewalking.shop.entity.Manager;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ManagerRepository extends CrudRepository<Manager,Long> {

}
