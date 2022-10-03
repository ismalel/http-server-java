package com.encora.httpserver.repository;

import com.encora.httpserver.model.User;

import java.util.List;

public interface UserRepository {

    String createUser(User user);
    User findUserById(String id);
    List<User> getUsers();
}
