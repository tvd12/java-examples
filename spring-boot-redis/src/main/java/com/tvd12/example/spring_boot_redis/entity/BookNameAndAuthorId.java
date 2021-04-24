package com.tvd12.example.spring_boot_redis.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookNameAndAuthorId {
    private String bookName;
    private long authorId;
}
