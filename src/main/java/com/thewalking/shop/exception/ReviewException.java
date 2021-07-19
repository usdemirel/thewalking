package com.thewalking.shop.exception;

import java.util.function.Supplier;

public class ReviewException extends RuntimeException implements Supplier<ReviewException> {
    public ReviewException(String message) {
        super("Review Exception: " + message);
    }

    public ReviewException(String message, Throwable cause) {
        super("Review Exception: " + message, cause);
    }

    @Override
    public ReviewException get() {
        return this;
    }



}
