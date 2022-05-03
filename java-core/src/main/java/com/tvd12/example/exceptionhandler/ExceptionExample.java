package com.tvd12.example.exceptionhandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

public class ExceptionExample {
    public static void validateEmail(String email) {
        if (!Pattern.compile("^(.+)@(.+)$").matcher(email).matches()) {
            throw new IllegalArgumentException("invalid email: " + email);
        }
    }

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(ExceptionExample.class);
        try {
            validateEmail("dzung@youngmonkeys.org");
            validateEmail("dzung");
        } catch (Exception e) {
            logger.error("validate email failed", e);
        }
    }
}
