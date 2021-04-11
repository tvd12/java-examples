package com.tvd12.example.spring_boot.repository;

import com.tvd12.example.spring_boot.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookRepository {

    public void save(Book book) {
        System.out.println("save book: " + book);
    }
}
