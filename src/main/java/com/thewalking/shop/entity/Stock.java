package com.thewalking.shop.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
public class Stock implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade=CascadeType.DETACH)
    private Product product;
    @OneToOne(cascade=CascadeType.DETACH)
    private Branch branch;
    private String barcode;
    private double price;
    private int quantity;
    private LocalDate salesStartDate;
    private LocalDate salesEndDate;
    private double salesPrice;

}
