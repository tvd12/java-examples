package com.example.mvc.exception;

public class AuthenticationException extends RuntimeException {

    public AuthenticationException() {
        super("username or password is incorrect");
    }
}
