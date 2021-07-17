package com.thewalking.shop.service;

import com.thewalking.shop.dto.EmployeeDto;
import com.thewalking.shop.entity.Employee;
import com.thewalking.shop.entity.User;
import com.thewalking.shop.exception.ErrorMessages;
import com.thewalking.shop.exception.UserException;
import com.thewalking.shop.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;
import static com.thewalking.shop.model.Roles.*;

@Service(value = "employeeService")
@Slf4j
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    private boolean hasAllRoles(String... roles){
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        List<String> auths = authorities.stream().map(each -> each.getAuthority()).collect(Collectors.toList());

        for(String role: roles){
            System.out.print(role + " --> ");
            if(!auths.contains("ROLE_" + role)) return false;
            System.out.println("YES");
        }

        return true;
    }

    @Override
    public Employee save(EmployeeDto employee) {
        if((hasAllRoles("MANAGER",employee.getBranch().getId().toString()) && employee.getRole().equals("HIREREQ"))
                || (hasAllRoles("OWNER") && Arrays.asList("EMPLOYEE","MANAGER","OWNER","HIREREQ").contains (employee.getRole()))){

            System.out.println("*********");
            return employeeRepository.save(convertToEmployee(employee));

        }else throw new UserException(ErrorMessages.NO_AUTHORIZATION.getErrorMessage());
    }

    @Override
    public Employee update(Employee employee) {
        if (employee.getId() == null) throw new UserException("No such Employee identified");
        User currentRecord = employeeRepository.findById(employee.getId()).orElseThrow(
                new UserException("No record found with the given ID")
        );

        if (hasAllRoles(OWNER.getRole()) || (hasAllRoles(MANAGER.getRole(), employee.getBranch().getId().toString()) &&
                Arrays.asList("FIREREQ",employeeRepository.findById(employee.getId()).get().getRole()).contains (employee.getRole()))) {
            System.out.println("*********");

            employee.setPassword(currentRecord.getPassword());
            return employeeRepository.save(employee);
        }else throw new UserException(ErrorMessages.NO_AUTHORIZATION.getErrorMessage());
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
        return employeeRepository.findById(id).orElseThrow(
                new UserException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage()).get()
        );
    }

    @Override
    public Employee toggleUserActivenessById(Long id) {
        Optional<Employee> changed = employeeRepository.findById(id).flatMap(user -> {
            user.setActive(!user.isActive());
            return Optional.of(user);
        });
        return changed.orElseThrow(() -> {
            throw new UserException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        });
    }

    @Override
    public List<Employee> findEmployeesByBranchIdAndActiveIsTrue(Long branchId) {
        if (hasAllRoles("MANAGER", branchId.toString()) || hasAllRoles("OWNER")) {
            List<Employee> list = new ArrayList<>();
            employeeRepository.findAllByBranchId(branchId)
                    .iterator().forEachRemaining(each -> {
                if (each.isActive()) list.add(each);
            });
            return list;
        } else throw new UserException(ErrorMessages.NO_AUTHORIZATION.getErrorMessage());
    }

    private Employee convertToEmployee(EmployeeDto employeeDto){
        Employee newEmployee = new Employee();
        newEmployee.setEmail(employeeDto.getEmail());
        newEmployee.setPassword(bcryptEncoder.encode(employeeDto.getPassword()));
        newEmployee.setRole(employeeDto.getRole());
        newEmployee.setFirstName(employeeDto.getFirstName());
        newEmployee.setLastName(employeeDto.getLastName());
        newEmployee.setPhone(employeeDto.getPhone());
        newEmployee.setActive(employeeDto.isActive());
        newEmployee.setAddress(employeeDto.getAddress());
        newEmployee.setBranch(employeeDto.getBranch());
        newEmployee.setPassword(bcryptEncoder.encode(employeeDto.getPassword()));
        return newEmployee;
    }

}
