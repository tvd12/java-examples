package com.tvd12.example.spring_boot.service;

import com.tvd12.example.spring_boot.entity.Book;
import com.tvd12.example.spring_boot.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public void saveBook(Book book) {
        bookRepository.save(book);
    }
}
