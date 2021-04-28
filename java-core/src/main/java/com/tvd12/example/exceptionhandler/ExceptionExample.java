package com.tvd12.example.exceptionhandler;

import java.util.regex.Pattern;

public class ExceptionExample {
    public static void validateEmail(String email) {
        if(!Pattern.compile("^(.+)@(.+)$").matcher(email).matches()) {
            throw new IllegalArgumentException("invalid email: " + email);
        }
    }

    public static void main(String[] args) {
        validateEmail("dzung@youngmonkeys.org");
        validateEmail("dzung");
    }
}
