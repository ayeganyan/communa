package com.communa.server.exception;

public class ResidentNotFoundException extends RuntimeException{
    public ResidentNotFoundException(String message) {
        super(message);
    }
}
