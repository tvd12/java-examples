package com.tvd12.example.spring_boot_redis.controller;

import com.tvd12.example.spring_boot_redis.entity.Author;
import com.tvd12.example.spring_boot_redis.repository.AuthorRepository;
import com.tvd12.example.spring_boot_redis.request.AddAuthorRequest;
import com.tvd12.example.spring_boot_redis.repository.RedisAtomicLong;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/author")
public class AuthorController {

    private final RedisAtomicLong idGentor;
    private final AuthorRepository authorRepository;

    @PostMapping("/add")
    public Author addAuthor(@RequestBody AddAuthorRequest request) {
        Author author = new Author(
            idGentor.incrementAndGet("author"),
            request.getAuthorName()
        );
        authorRepository.save(author);
        return author;
    }

}