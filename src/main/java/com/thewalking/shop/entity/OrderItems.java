package com.thewalking.shop.entity;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class OrderItems implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.DETACH)
    private Stock stock;
    private int quantity;
    @OneToOne(cascade = CascadeType.DETACH)
    private Customer customer;
    private double paidSubtotal;
    @ManyToOne(cascade = CascadeType.DETACH)
    private Orders order;
}
