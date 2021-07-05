package com.thewalking.shop.entity;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
@Data
public class Employee extends User{
    @OneToOne(cascade = CascadeType.DETACH)
    private Branch branch;


}
