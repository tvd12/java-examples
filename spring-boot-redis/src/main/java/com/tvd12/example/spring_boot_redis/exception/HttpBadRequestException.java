package com.tvd12.example.spring_boot_redis.exception;

public class HttpBadRequestException extends RuntimeException {
    public HttpBadRequestException(String msg) {
        super(msg);
    }
}
