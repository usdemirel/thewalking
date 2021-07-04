package com.thewalking.shop.security.service;



import com.thewalking.shop.security.model.Roles;
import com.thewalking.shop.security.model.User;
import com.thewalking.shop.security.model.UserDto;

import java.util.List;

public interface UserService {

    User save(UserDto user) throws Exception;
    List<User> findAll();
    void delete(long id);
    User findOne(String username);

    User findById(Long id);
    User makeUserInActive(User user);
    User makeUserInActiveById(Long id);
    public User changeUserRole(Long id, Roles role);
}
