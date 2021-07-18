package com.thewalking.shop.exception;

import java.util.function.Supplier;

public class StockException extends RuntimeException implements Supplier<StockException> {
    public StockException(String message) {
        super("Stock Exception: " + message);
    }

    public StockException(String message, Throwable cause) {
        super("Stock Exception: " + message, cause);
    }

    @Override
    public StockException get() {
        return this;
    }
}
