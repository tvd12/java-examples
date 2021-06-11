package com.tvd12.example.common;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tvd12.ezyfox.stream.EzyAnywayInputStreamLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public final class JsonReader {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private JsonReader() {
    }

    public static  <T> List<T> readList(String file, Class<T> outType) {
        final InputStream inputStream = new EzyAnywayInputStreamLoader()
            .load(file);
        try {
            return OBJECT_MAPPER
                .readValue(inputStream, new TypeReference<List<T>>(){});
        }
        catch (IOException e) {
            throw new IllegalArgumentException("can not read json file: " + file + " to: " + outType.getSimpleName());
        }
    }

    public static  <T> T read(String file, Class<T> outType) {
        final InputStream inputStream = new EzyAnywayInputStreamLoader()
            .load(file);
        try {
            return OBJECT_MAPPER
                .readValue(inputStream, outType);
        }
        catch (IOException e) {
            throw new IllegalArgumentException("can not read json file: " + file + " to: " + outType.getSimpleName());
        }
    }

}
