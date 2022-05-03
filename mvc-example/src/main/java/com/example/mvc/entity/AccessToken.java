package com.example.mvc.entity;

public class AccessToken {

    public String accessToken;
    private long userId;
    private long expiredTime;

    public AccessToken() {}

    public AccessToken(String accessToken, long userId, long expiredTime) {
        this.accessToken = accessToken;
        this.userId = userId;
        this.expiredTime = expiredTime;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(long expiredTime) {
        this.expiredTime = expiredTime;
    }
}
