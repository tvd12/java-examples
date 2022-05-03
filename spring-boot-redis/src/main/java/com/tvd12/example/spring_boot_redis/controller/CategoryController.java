package com.tvd12.example.spring_boot_redis.controller;

import com.tvd12.example.spring_boot_redis.entity.Category;
import com.tvd12.example.spring_boot_redis.request.AddCategoryRequest;
import com.tvd12.example.spring_boot_redis.exception.HttpBadRequestException;
import com.tvd12.example.spring_boot_redis.repository.CategoryIdByNameRepository;
import com.tvd12.example.spring_boot_redis.repository.CategoryRepository;
import com.tvd12.example.spring_boot_redis.repository.RedisAtomicLong;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    private final RedisAtomicLong idGentor;
    private final CategoryRepository categoryRepository;
    private final CategoryIdByNameRepository categoryIdByNameRepository;

    @PostMapping("/add")
    public Category addCategory(@RequestBody AddCategoryRequest request) {
        Long existedCategoryId = categoryIdByNameRepository
            .findById(request.getCategoryName())
            .orElse(null);
        if (existedCategoryId != null) {
            throw new HttpBadRequestException(
                "category named: " + request.getCategoryName() + " existed"
            );
        }
        Category category = new Category(
            idGentor.incrementAndGet("category"),
            request.getCategoryName()
        );
        categoryRepository.save(category);
        categoryIdByNameRepository.put(category.getName(), category.getId());
        return category;
    }

}