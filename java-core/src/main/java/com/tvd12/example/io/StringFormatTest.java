package com.tvd12.example.io;

public class StringFormatTest {
    public static void main(String[] args) {
        System.out.printf("sitemap-%s.xml", "index");
        System.out.println("Dung".matches("[0-9a-zA-Z]+"));
        System.out.println("Dũng".matches("[0-9a-zA-Z]+"));
        System.out.println("Dung Name".matches("[0-9a-zA-Z\\s]+"));
    }
}
