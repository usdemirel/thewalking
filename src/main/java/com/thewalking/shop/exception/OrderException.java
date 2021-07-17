package com.thewalking.shop.exception;

import java.util.function.Supplier;

public class OrderException extends RuntimeException implements Supplier<OrderException> {
    public OrderException(String message) {
        super("Order Exception: " + message);
    }

    public OrderException(String message, Throwable cause) {
        super("Order Exception: " + message, cause);
    }

    @Override
    public OrderException get() {
        return this;
    }



}
