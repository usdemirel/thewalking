package com.thewalking.shop.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @ManyToOne
    private Product product;
    @OneToOne
    private Branch branch;
    private String barcode;
    private double price;
    private int quantity;
    private LocalDate salesStartDate;
    private LocalDate salesEndDate;
    private double salesPrice;

}
