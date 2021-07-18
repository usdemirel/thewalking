package com.thewalking.shop.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StockDiscountDto {
    private Long Id;
    Double salesPrice;
    LocalDate salesStartDate;
    LocalDate salesEndDate;
}
