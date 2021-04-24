package com.tvd12.example.spring_boot_redis.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tvd12.example.spring_boot_redis.entity.BookNameAndAuthorId;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
@AllArgsConstructor
public class BookIdByNameAndAuthorIdRepository {
    private final ObjectMapper objectMapper;
    private final RedisTemplate redisTemplate;
    private final static String MAP_NAME = "SpringBootRedis.BookIdByNameAndAuthorId";

    public Optional<Long> findById(BookNameAndAuthorId id) {
        try {
            String idString = objectMapper.writeValueAsString(id);
            return Optional.ofNullable(
                (Long)redisTemplate
                    .opsForHash()
                    .get(MAP_NAME, idString)
            );
        }
        catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void put(BookNameAndAuthorId id, Long bookId) {
        try {
            String idString = objectMapper.writeValueAsString(id);
            redisTemplate.opsForHash()
                .put(MAP_NAME, idString, bookId);
        }
        catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
