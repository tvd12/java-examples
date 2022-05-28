package com.tvd12.example.spring_webflux_jpa.repository;

import com.tvd12.example.spring_webflux_jpa.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {}
