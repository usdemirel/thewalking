package com.thewalking.shop.service;



import com.thewalking.shop.model.Roles;
import com.thewalking.shop.entity.User;
import com.thewalking.shop.dto.UserDto;

import java.util.List;

public interface UserService {

    User save(UserDto user) throws Exception;
    List<User> findAll();
    void delete(long id);
    User findOne(String username);

    User findById(Long id);
    User makeUserInActive(User user);
    User toggleUserActivenessById(Long id);
    User changeUserRole(Long id, String role);
}
