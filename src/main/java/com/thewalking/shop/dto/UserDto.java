package com.thewalking.shop.dto;

import com.thewalking.shop.entity.Address;
import lombok.Data;

@Data
public class UserDto {


    private String email;
    private String password;
    private String role;
    private boolean isActive;
    private String firstName;
    private String lastName;
    private String phone;
    private Address address;

}
