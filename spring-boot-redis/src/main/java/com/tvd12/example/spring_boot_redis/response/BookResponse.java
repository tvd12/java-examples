package com.tvd12.example.spring_boot_redis.response;

import com.tvd12.example.spring_boot_redis.entity.Author;
import com.tvd12.example.spring_boot_redis.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.*;

@Getter
@AllArgsConstructor
public class BookResponse {
    private Long bookId;
    private Category category;
    private Author author;
    private String bookName;
    private BigDecimal price;
    private Date releaseTime;
}