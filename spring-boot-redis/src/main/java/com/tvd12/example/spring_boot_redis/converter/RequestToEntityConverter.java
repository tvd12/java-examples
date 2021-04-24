package com.tvd12.example.spring_boot_redis.converter;

import com.tvd12.example.common.DateConverter;
import com.tvd12.example.spring_boot_redis.entity.Book;
import com.tvd12.example.spring_boot_redis.request.AddBookRequest;
import org.springframework.stereotype.Component;

@Component
public class RequestToEntityConverter {
	public Book toBookEntity(AddBookRequest request, Long bookId) {
	    return new Book(
	        bookId,
	        request.getCategoryId(),
	        request.getAuthorId(),
	        request.getBookName(),
	        request.getPrice(),
	        DateConverter.toLocalDate(request.getReleaseDate()),
	        DateConverter.toLocalDateTime(request.getReleaseTime())
	    );
	}
}