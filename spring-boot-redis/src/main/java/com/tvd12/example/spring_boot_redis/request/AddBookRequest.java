package com.tvd12.example.spring_boot_redis.request;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddBookRequest {
    private Long categoryId;
    private Long authorId;
    private String bookName;
    private BigDecimal price;
    private Date releaseDate;
    private Date releaseTime;
}