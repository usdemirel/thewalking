package com.thewalking.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thewalking.shop.model.Roles;
import com.thewalking.shop.utilityservices.ValueOfEnum;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Email
    @Column(unique=true)
    @NotEmpty
    private String email;
    @Column
    @JsonIgnore
    private String password;
    @Column
    @ValueOfEnum(enumClass = Roles.class)
    private String role;
    private boolean isActive;
    private String firstName;
    private String lastName;
    private String phone;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Address address;

}
