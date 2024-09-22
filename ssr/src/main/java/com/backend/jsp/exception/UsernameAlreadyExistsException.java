package com.backend.jsp.exception;

public class UsernameAlreadyExistsException extends Exception{
    public UsernameAlreadyExistsException(String message) {
        super(message);
    }
}
