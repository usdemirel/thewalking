package com.thewalking.shop.exception;

import java.util.function.Supplier;

public class ProductException extends RuntimeException implements Supplier<ProductException> {
    public ProductException(String message) {
        super("Product Exception: " + message);
    }

    @Override
    public ProductException get() {
        return this;
    }
}
