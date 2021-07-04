package com.thewalking.shop.exception;

public class UserException extends RuntimeException{
    public UserException(String message) {
        super("User Exception: " + message);
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
    }


}
