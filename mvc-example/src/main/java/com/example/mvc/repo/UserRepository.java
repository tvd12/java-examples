package com.example.mvc.repo;

import com.example.mvc.entity.User;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class UserRepository {

    private final Map<Long, User> userById =
        new ConcurrentHashMap<>();
    private final Map<String, User> userByUsernameIndex =
        new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    public UserRepository() {
        save(new User(0, "vuluong", "123456"));
    }

    public void save(User user) {
        if (user.getId() <= 0) {
            user.setId(idGenerator.incrementAndGet());
        }
        userById.put(user.getId(), user);
        userByUsernameIndex.put(user.getUsername(), user);
    }

    public User findById(long userId) {
        return userById.get(userId);
    }

    public Optional<User> findByIdOptional(long userId) {
        return Optional.ofNullable(findById(userId));
    }

    public User findByUsername(String username) {
        return userByUsernameIndex.get(username);
    }

    public Optional<User> findByUsernameOptional(String username) {
        return Optional.ofNullable(findByUsername(username));
    }
}
