package com.thewalking.shop.service;

import com.thewalking.shop.dto.ManagerDto;
import com.thewalking.shop.entity.Employee;
import com.thewalking.shop.entity.Manager;
import com.thewalking.shop.exception.ErrorMessages;
import com.thewalking.shop.exception.UserException;
import com.thewalking.shop.repository.ManagerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Arrays;

@Service
@Slf4j
public class ManagerServiceImpl implements ManagerService{

    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Override
    public Manager save(ManagerDto manager) {
        Manager newManager = new Manager();
        newManager.setEmail(manager.getEmail());
        newManager.setPassword(bcryptEncoder.encode(manager.getPassword()));
        newManager.setRole(manager.getRole());
        newManager.setFirstName(manager.getFirstName());
        newManager.setLastName(manager.getLastName());
        newManager.setPhone(manager.getPhone());
        newManager.setActive(manager.isActive());
        newManager.setAddress(manager.getAddress());
        newManager.setBranch(manager.getBranch());
        return managerRepository.save(newManager);
    }
}
