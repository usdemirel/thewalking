package com.thewalking.shop.exception;

import java.util.function.Supplier;

public class UserException extends RuntimeException implements Supplier<UserException> {
    public UserException(String message) {
        super("User Exception: " + message);
    }

    public UserException(String message, Throwable cause) {
        super("User Exception: " + message, cause);
    }

    @Override
    public UserException get() {
        return this;
    }
}
