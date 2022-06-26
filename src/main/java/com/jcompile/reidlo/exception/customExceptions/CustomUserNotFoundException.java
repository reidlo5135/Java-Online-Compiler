package com.jcompile.reidlo.exception.customExceptions;

public class CustomUserNotFoundException extends RuntimeException{

    public CustomUserNotFoundException() {
        super();
    }

    public CustomUserNotFoundException(String message) {
        super(message);
    }

    public CustomUserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
