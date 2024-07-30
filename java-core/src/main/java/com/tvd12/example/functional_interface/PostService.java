package com.tvd12.example.functional_interface;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final EntityToModelConverter entityToModelConverter;

    public List<PostModel> getPosts() {
        int hasCode = postRepository.hashCode();
        return postRepository
            .getPosts()
            .stream()
            .map(entityToModelConverter::toModel)
            .collect(Collectors.toList());
    }
}
