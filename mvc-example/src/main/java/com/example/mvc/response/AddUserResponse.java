package com.example.mvc.response;

public class AddUserResponse {

    private final long userId;

    public AddUserResponse(long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "AddUserResponse{" +
            "userId=" + userId +
            '}';
    }
}
