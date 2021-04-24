package com.tvd12.example.spring_boot_redis.repository;

import com.tvd12.example.spring_boot_redis.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
