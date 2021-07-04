package com.thewalking.shop.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

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
