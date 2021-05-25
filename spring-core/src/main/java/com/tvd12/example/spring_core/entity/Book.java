package com.tvd12.example.spring_core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {
    private long bookId;
    private String bookName;
}
