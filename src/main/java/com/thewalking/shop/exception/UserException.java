package com.thewalking.shop.exception;

import org.springframework.http.HttpStatus;

public class UserException extends RuntimeException{
    public UserException(String message) {
        super("User Exception: " + message);
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
    }


}
