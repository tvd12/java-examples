package com.tvd12.example.spring_boot;

import com.tvd12.example.spring_boot.entity.Book;
import com.tvd12.example.spring_boot.service.BookService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration(
    exclude = {
        RestTemplateAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class
    }
)
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
