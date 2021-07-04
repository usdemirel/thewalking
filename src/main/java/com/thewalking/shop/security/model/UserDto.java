package com.thewalking.shop.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

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
