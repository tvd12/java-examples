package com.tvd12.example.spring_boot_redis.controller;

import com.tvd12.example.spring_boot_redis.converter.EntityToResponseConverter;
import com.tvd12.example.spring_boot_redis.converter.RequestToEntityConverter;
import com.tvd12.example.spring_boot_redis.entity.Author;
import com.tvd12.example.spring_boot_redis.entity.Book;
import com.tvd12.example.spring_boot_redis.entity.BookNameAndAuthorId;
import com.tvd12.example.spring_boot_redis.entity.Category;
import com.tvd12.example.spring_boot_redis.exception.HttpBadRequestException;
import com.tvd12.example.spring_boot_redis.exception.HttpNotFoundException;
import com.tvd12.example.spring_boot_redis.repository.*;
import com.tvd12.example.spring_boot_redis.request.AddBookRequest;
import com.tvd12.example.spring_boot_redis.response.BookResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class BookController {

    private final RedisAtomicLong idGentor;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final BookIdByNameAndAuthorIdRepository bookIdByNameAndAuthorIdRepository;
    private final EntityToResponseConverter entityToResponseConverter;
    private final RequestToEntityConverter requestToEntityConverter;

    @PostMapping("/book/add")
    public BookResponse addBook(@RequestBody AddBookRequest request) {
        BookNameAndAuthorId bookNameAndAuthorId = new BookNameAndAuthorId(
            request.getBookName(),
            request.getAuthorId()
        );
        Long existedBookId = bookIdByNameAndAuthorIdRepository.findById(bookNameAndAuthorId)
            .orElse(null);
        if (existedBookId != null) {
            throw new HttpBadRequestException(
                "author: " + request.getAuthorId() +
                    " has already registered book: " + request.getBookName()
            );
        }
        Author author = authorRepository.findById(request.getAuthorId())
            .orElseThrow(() ->
                new HttpBadRequestException(
                    "author: " + request.getAuthorId() + " not found"
                )
            );

        Category category = categoryRepository.findById(request.getCategoryId())
            .orElseThrow(() ->
                new HttpBadRequestException(
                    "category: " + request.getCategoryId() + " not found"
                )
            );

        Long bookId = idGentor.incrementAndGet("book");
        Book book = requestToEntityConverter.toBookEntity(request, bookId);
        bookRepository.save(book);
        bookIdByNameAndAuthorIdRepository.put(bookNameAndAuthorId, bookId);
        return entityToResponseConverter.toBookResponse(book, author, category);
    }

    @GetMapping("/books/{bookId}")
    public BookResponse getBook(@PathVariable Long bookId) {
        Book book = bookRepository.findById(bookId)
            .orElseThrow(() ->
                new HttpNotFoundException("not found book with id: " + bookId)
            );
        Author author = authorRepository.findById(book.getAuthorId())
            .orElseThrow(() ->
                new IllegalStateException("maybe someone has change redis")
            );
        Category category = categoryRepository.findById(book.getCategoryId())
            .orElseThrow(() ->
                new IllegalStateException("maybe someone has change redis")
            );
        return entityToResponseConverter.toBookResponse(
            book,
            author,
            category
        );
    }
}