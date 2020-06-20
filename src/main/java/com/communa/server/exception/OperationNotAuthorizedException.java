package com.communa.server.exception;

public class OperationNotAuthorizedException extends RuntimeException{
    public OperationNotAuthorizedException(String message) {
        super(message);
    }
}
