package com.tvd12.example.spring_core.service;

import com.tvd12.example.spring_core.entity.Book;
import com.tvd12.example.spring_core.repo.BookRepo;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Setter
@Service
public class BookService {

    @Autowired
    private BookRepo bookRepo;

    public Book getBookById(long bookId) {
        return bookRepo.findById(bookId);
    }
}
