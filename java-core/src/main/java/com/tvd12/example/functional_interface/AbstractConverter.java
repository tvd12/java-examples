package com.tvd12.example.functional_interface;

public abstract class AbstractConverter implements Converter {

    @Override
    public PostModel checkAndConvert(PostEntity entity) {
        if (entity == null) {
            throw new NullPointerException("entity is null");
        }
        return convert(entity);
    }
}
