package com.example.mvc.repo;

import com.example.mvc.entity.AccessToken;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AccessTokenRepository {

    private final Map<String, AccessToken> tokenById =
        new ConcurrentHashMap<>();

    public void save(AccessToken accessToken) {
        tokenById.put(accessToken.getAccessToken(), accessToken);
    }
}
