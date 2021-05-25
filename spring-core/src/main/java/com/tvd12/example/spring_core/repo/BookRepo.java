package com.tvd12.example.spring_core.repo;

import com.tvd12.example.spring_core.entity.Book;
import com.tvd12.ezyfox.util.EzyMapBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class BookRepo {
    private final Map<Long, Book> bookById;

    @SuppressWarnings("unchecked")
    public BookRepo() {
        this.bookById = EzyMapBuilder.mapBuilder()
            .put(1L, new Book(1L, "Java in Action"))
            .put(2L, new Book(2L, "Kotlin in Action"))
            .build();
    }

    public Book findById(Long id) {
        return bookById.get(id);
    }
}
