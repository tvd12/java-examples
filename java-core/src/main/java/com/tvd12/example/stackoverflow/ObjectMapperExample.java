package com.tvd12.example.stackoverflow;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.ToString;

import java.util.List;

public class ObjectMapperExample {

    public static void main(String[] args) throws Exception {
        String json = "{\n" +
            "    \"Data\": [{\"ID\":\"vt\", \"Name\":\"vuong\"}, {\"ID\":\"ha\", \"Name\":\"hoang\"}],\n" +
            "    \"ErrorCode\": null,\n" +
            "    \"ErrorMsg\": null\n" +
            "}";
        ObjectMapper objectMapper = new ObjectMapper();

        System.out.println(objectMapper.readValue(json, ApiResponse.class));
    }

    @ToString
    public static class ApiResponse {
        @JsonProperty("Data")
        private List<User> data;
        @JsonProperty("ErrorCode")
        private String errorCode;
        @JsonProperty("ErrorMsg")
        private String errorMess;
    }

    @ToString
    private static class User {
        @JsonProperty("ID")
        private String id;
        @JsonProperty("Name")
        private String name;
    }
}
