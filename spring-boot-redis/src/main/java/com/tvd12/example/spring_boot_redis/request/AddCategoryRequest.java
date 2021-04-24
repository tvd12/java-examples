package com.tvd12.example.spring_boot_redis.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddCategoryRequest {
    private String categoryName;
}