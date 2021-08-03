package com.thewalking.shop.model;

import com.thewalking.shop.entity.Branch;
import com.thewalking.shop.entity.ProductDescription;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StockDetails {
    private Long id;
    private double price;
    private int quantity;
    private Branch branch;
    private String size;
    private int maxOrderQuantity;

}
