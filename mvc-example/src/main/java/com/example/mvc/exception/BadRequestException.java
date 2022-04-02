package com.example.mvc.exception;

import java.util.Map;

public class BadRequestException extends IllegalArgumentException {

    private final Map<String, String> errors;

    public BadRequestException(Map<String, String> errors) {
        super(errors.toString());
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
