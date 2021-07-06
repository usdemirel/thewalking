package com.thewalking.shop.entity;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Data
public class Manager extends User{

    @OneToOne(cascade = CascadeType.DETACH)
    private Branch branch;

}
