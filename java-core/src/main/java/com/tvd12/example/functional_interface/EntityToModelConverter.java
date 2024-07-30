package com.tvd12.example.functional_interface;

public class EntityToModelConverter {

    public PostModel toModel(PostEntity entity) {
        return PostModel.builder()
            .id(entity.getId())
            .build();
    }
}
