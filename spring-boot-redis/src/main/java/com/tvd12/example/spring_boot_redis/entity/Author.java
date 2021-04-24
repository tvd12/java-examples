package com.tvd12.example.spring_boot_redis.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Setter
@RedisHash("SpringBootRedis.Author")
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    @Id
    private long id;
    private String name;
}