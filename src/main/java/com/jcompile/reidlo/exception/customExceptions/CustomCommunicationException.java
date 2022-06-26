package com.jcompile.reidlo.exception.customExceptions;

public class CustomCommunicationException extends RuntimeException {
    public CustomCommunicationException() {
        super();
    }

    public CustomCommunicationException(String message) {
        super(message);
    }

    public CustomCommunicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
