package com.jcompile.reidlo.exception.customExceptions;

public class CustomEmailSignUpFailedException extends RuntimeException{

    public CustomEmailSignUpFailedException() {
        super();
    }

    public CustomEmailSignUpFailedException(String message) {
        super(message);
    }

    public CustomEmailSignUpFailedException(String mesasge, Throwable throwable) {
        super(mesasge, throwable);
    }
}
