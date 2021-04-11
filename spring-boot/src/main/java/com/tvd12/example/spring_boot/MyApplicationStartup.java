package com.tvd12.example.spring_boot;

import com.tvd12.example.spring_boot.entity.Book;
import com.tvd12.example.spring_boot.service.BookService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootConfiguration
public class MyApplicationStartup {
    public static void main(String[] args) {
        ApplicationContext appContext =
                SpringApplication.run(MyApplicationStartup.class, args);
        BookService bookService = appContext.getBean(BookService.class);
        bookService.saveBook(new Book(1, "youngmonkeys.org"));
    }
}
