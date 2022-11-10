package com.encora.httpserver.service;

import com.encora.httpserver.framework.annotation.Service;
import com.encora.httpserver.model.User;
import com.encora.httpserver.repository.UserRepository;

import java.util.List;

@Service
public class UserService {


    private final UserRepository userRepository = new UserRepository();

    public User create(User user) {
        return userRepository.save(user);
    }

    public User getUser(Long id) {
        return userRepository.getById(id);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
