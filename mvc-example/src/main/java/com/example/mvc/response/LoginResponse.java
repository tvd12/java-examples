package com.example.mvc.response;

public class LoginResponse {

    private final String accessToken;

    public LoginResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
            "accessToken='" + accessToken + '\'' +
            '}';
    }
}
