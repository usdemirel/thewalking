package com.thewalking.shop.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private ProductDescription productDescription;
    private String SKU;
    private String Size;
    private int maxOrderQuantity;



}
