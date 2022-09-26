package com.encora.httpserver.data.user;

import com.encora.httpserver.domain.user.IUserRepository;
import com.encora.httpserver.domain.user.User;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class UserRepository implements IUserRepository {

    private static final Map USERS_DB = new ConcurrentHashMap();

    @Override
    public String createUser(User user) {
        String id = UUID.randomUUID().toString();
        user.setId(id);
        System.out.println("sedf + " + user.toString());

        USERS_DB.put(id, user);

        return id;
    }

    @Override
    public User findUserById(String id) {
        return (User) USERS_DB.get(id);
    }
}
