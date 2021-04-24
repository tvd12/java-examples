package com.tvd12.example.spring_boot_redis.exception;

public class HttpNotFoundException extends RuntimeException {
    public HttpNotFoundException(String msg) {
        super(msg);
    }
}
