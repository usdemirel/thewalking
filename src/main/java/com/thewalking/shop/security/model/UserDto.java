package com.thewalking.shop.security.model;

import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class UserDto {

    private String email;
    private String password;
    private int age;
    private int salary;
    private String role;

}
