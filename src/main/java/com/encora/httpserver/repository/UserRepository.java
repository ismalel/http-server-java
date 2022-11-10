package com.encora.httpserver.repository;

import com.encora.httpserver.framework.annotation.Repository;
import com.encora.httpserver.framework.data.FrameworkRepository;
import com.encora.httpserver.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Repository
public class UserRepository implements FrameworkRepository<User, Long> {

    private static final List<User> USERS_DB = new ArrayList<>();


    @Override
    public List<User> findAll() {
        return USERS_DB;
    }

    @Override
    public User getById(Long id) {
        for (User u : USERS_DB) {
            if (Objects.equals(u.getId(), id)) {
                return u;
            }
        }
        return null;
    }

    @Override
    public User save(User user) {
        Long id = UUID.randomUUID().getMostSignificantBits();
        user.setId(id);

        USERS_DB.add(user);
        return user;
    }
}
