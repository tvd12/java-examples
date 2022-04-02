package com.example.mvc.model;

public class UserModel {

    private final long userId;
    private final String username;
    private final String password;

    public UserModel(long userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
