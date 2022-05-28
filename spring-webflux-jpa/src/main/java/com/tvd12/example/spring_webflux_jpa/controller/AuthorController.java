package com.tvd12.example.spring_webflux_jpa.controller;

import com.tvd12.example.spring_webflux_jpa.entity.Author;
import com.tvd12.example.spring_webflux_jpa.repository.AuthorRepository;
import com.tvd12.example.spring_webflux_jpa.request.AddAuthorRequest;
import com.tvd12.example.spring_webflux_jpa.response.AddAuthorResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class AuthorController {

    private final AuthorRepository authorRepository;

    @PostMapping("/author/add")
    public Mono<AddAuthorResponse> authorsAddPost(
        @RequestBody AddAuthorRequest request
    ) {
        Author author = new Author();
        author.setName(request.getName());
        author.setCreatedTime(LocalDateTime.now());
        author.setUpdatedTime(LocalDateTime.now());
        Author saved = authorRepository.save(author);
        return Mono.just(new AddAuthorResponse(saved.getId()));
    }

    @GetMapping("/author/{authorId}")
    public Mono<ResponseEntity<Author>> authorsAuthorIdGet(
        @PathVariable("authorId") long authorId
    ) {
        return authorRepository.findById(authorId)
            .map(it ->
                Mono.just(
                    ResponseEntity.ok(it)
                )
            ).orElseGet(() ->
                Mono.just(
                    ResponseEntity.notFound()
                        .build()
                )
            );
    }
}
