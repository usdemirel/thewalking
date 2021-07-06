package com.thewalking.shop.service;

import com.thewalking.shop.dto.ManagerDto;
import com.thewalking.shop.entity.Employee;
import com.thewalking.shop.entity.Manager;

import java.util.List;

public interface ManagerService {
    Manager save(ManagerDto manager);

}
