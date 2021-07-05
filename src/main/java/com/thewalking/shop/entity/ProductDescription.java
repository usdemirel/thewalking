package com.thewalking.shop.entity;

import jdk.jfr.Category;
import lombok.Data;
import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class ProductDescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateListed;
    private String title;
    private String description;
    private String image;
    private boolean isActive;
    private String tags;
    private String brand;
    @OneToMany
    private List<Categories> categories;
    @OneToMany
    private List<Review> reviews;
    @OneToOne
    private Manufacturer manufacturer;



}
