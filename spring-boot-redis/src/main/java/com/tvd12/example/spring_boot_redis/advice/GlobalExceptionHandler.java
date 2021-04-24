package com.tvd12.example.spring_boot_redis.advice;

import com.tvd12.example.spring_boot_redis.exception.HttpNotFoundException;
import com.tvd12.example.spring_boot_redis.exception.HttpBadRequestException;
import com.tvd12.ezyfox.util.EzyLoggable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends EzyLoggable {

    @ExceptionHandler(HttpBadRequestException.class)
    public ResponseEntity handleBadRequestException(HttpBadRequestException e) {
        logger.info("bad request: {}", e);
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(HttpNotFoundException.class)
    public ResponseEntity handleNotFoundException(HttpNotFoundException e) {
        logger.info("not found: {}", e);
        return ResponseEntity.notFound().build();
    }
}
