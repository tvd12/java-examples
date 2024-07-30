package com.tvd12.example.functional_interface;

public interface Converter {

    PostModel convert(PostEntity entity);

    PostModel checkAndConvert(PostEntity entity);
}
