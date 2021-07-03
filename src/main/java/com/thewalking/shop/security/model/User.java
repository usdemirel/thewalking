package com.thewalking.shop.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Data
//@Table(name = "users")
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
    private long salary;
    @Column
    @Max(value = 1000)
    private int age;
    @Column
    @ValueOfEnum(enumClass = Roles.class)
    private String role;

}
