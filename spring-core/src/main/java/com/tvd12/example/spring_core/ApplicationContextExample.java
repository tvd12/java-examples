package com.tvd12.example.spring_core;

import com.tvd12.example.spring_core.service.BookService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

public class ApplicationContextExample {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.tvd12.example.spring_core.config");
        context.scan("com.tvd12.example.spring_core.repo");
        context.scan("com.tvd12.example.spring_core.service");
        context.refresh();
        BookService bookService = context.getBean(BookService.class);
        System.out.println(bookService.getBookById(1));
        Environment environment = context.getEnvironment();
        String world = environment.getProperty("hello");
        System.out.println(world);
    }
}
