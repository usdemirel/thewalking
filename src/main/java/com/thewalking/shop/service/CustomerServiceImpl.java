package com.thewalking.shop.service;

import com.thewalking.shop.dto.UserDto;
import com.thewalking.shop.entity.Customer;
import com.thewalking.shop.entity.User;
import com.thewalking.shop.exception.UserException;
import com.thewalking.shop.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Override
    public Customer save(UserDto userdto) {
        Customer customer = new Customer();
        customer.setEmail(userdto.getEmail());
        customer.setPassword(bcryptEncoder.encode(userdto.getPassword()));
        customer.setRole(userdto.getRole());
        customer.setFirstName(userdto.getFirstName());
        customer.setLastName(userdto.getLastName());
        customer.setPhone(userdto.getPhone());
        customer.setActive(userdto.isActive());
        customer.setAddress(userdto.getAddress());
        return customerRepository.save(customer);
    }

    @Override
    public Customer update(Customer customer) throws Exception {
        if(customer.getId()==null) throw new UserException("No ID identified");
        User currentRecord = customerRepository.findById(customer.getId()).orElseThrow(
                new UserException("No record found with the given ID")
        );
        customer.setPassword(currentRecord.getPassword());
        return customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> list = new ArrayList<>();
        customerRepository.findAll().iterator().forEachRemaining(each -> list.add(each));
        return list;
    }

    @Override
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }
}
