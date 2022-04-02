package com.example.mvc.model;

public class AddUserModel {

    private String username;
    private String password;

    public AddUserModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
