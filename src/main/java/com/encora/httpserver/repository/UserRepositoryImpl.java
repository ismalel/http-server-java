package com.encora.httpserver.repository;

import com.encora.httpserver.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class UserRepositoryImpl implements UserRepository {

    private static final List<User> USERS_DB = new ArrayList<>();

    @Override
    public Long createUser(User user) {
        Long id = UUID.randomUUID().getMostSignificantBits();
        user.setId(id);

        USERS_DB.add(user);

        return id;
    }

    @Override
    public User findUserById(Long id) {
        for (User u : USERS_DB) {
            if (Objects.equals(u.getId(), id)) {
                return u;
            }
        }
        return null;
    }

    @Override
    public List<User> getUsers() {
        return USERS_DB;
    }
}
