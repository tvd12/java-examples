package com.example.mvc.model;

public class AccessTokenModel {

    private final String accessToken;
    private final long expiredTime;

    public AccessTokenModel(String accessToken, long expiredTime) {
        this.accessToken = accessToken;
        this.expiredTime = expiredTime;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public long getExpiredTime() {
        return expiredTime;
    }
}
