package com.thewalking.shop.dto;

import lombok.Data;

@Data
public class StockPriceQuantityDto {
    private Long Id;
    private double price;
    private int quantity;
}
