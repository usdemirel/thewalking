package com.thewalking.shop.model;

import com.thewalking.shop.entity.ProductDescription;
import lombok.Data;

import java.util.List;

@Data
public class StockDto {
    private ProductDescription productDescription;
    private List<StockDetails> stockDetailsList;
}
