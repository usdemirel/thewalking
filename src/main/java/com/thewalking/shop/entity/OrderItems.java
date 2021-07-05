package com.thewalking.shop.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Stock stock;
    private int quantity;
    @OneToOne
    private Customer customer;
    private double paidSubtotal;
    @ManyToOne
    private Orders order;
}
