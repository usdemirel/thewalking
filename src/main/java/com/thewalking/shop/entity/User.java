package com.thewalking.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thewalking.shop.model.Roles;
import com.thewalking.shop.utilityservices.ValueOfEnum;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Data
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    LocalDateTime timestamp;
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
