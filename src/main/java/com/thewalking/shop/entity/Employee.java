package com.thewalking.shop.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Data
public class Employee extends User{
    @OneToOne
    private Branch branch;
}
