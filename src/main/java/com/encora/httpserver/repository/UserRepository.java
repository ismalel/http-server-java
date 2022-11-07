package com.encora.httpserver.repository;

import com.encora.httpserver.model.User;

import java.util.List;

public interface UserRepository {

    Long createUser(User user);
    User findUserById(Long id);
    List<User> getUsers();
}
