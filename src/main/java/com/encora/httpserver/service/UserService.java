package com.encora.httpserver.service;

import com.encora.httpserver.model.User;
import com.encora.httpserver.repository.UserRepository;

import java.util.List;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String create(User user) {
        return userRepository.createUser(user);
    }

    public User getUser(String id) {
        return userRepository.findUserById(id);
    }

    public List<User> getUsers() {
        return userRepository.getUsers();
    }
}
