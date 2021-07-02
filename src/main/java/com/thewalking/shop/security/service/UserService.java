package com.thewalking.shop.security.service;



import com.thewalking.shop.security.model.User;
import com.thewalking.shop.security.model.UserDto;

import java.util.List;

public interface UserService {

    User save(UserDto user);
    List<User> findAll();
    void delete(long id);
    User findOne(String username);

    User findById(Long id);
}
