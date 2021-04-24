package com.tvd12.example.spring_boot_redis.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@RedisHash("SpringBootRedis.Book")
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    private Long id;
    private Long categoryId;
    private Long authorId;
    private String name;
    private BigDecimal price;
    private LocalDate releaseDate;
    private LocalDateTime releaseTime;
}