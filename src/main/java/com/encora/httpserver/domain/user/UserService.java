package com.encora.httpserver.domain.user;

public class UserService {

    private final IUserRepository IUserRepository;

    public UserService(IUserRepository IUserRepository) {
        this.IUserRepository = IUserRepository;
    }

    public String create(User user) {
        return IUserRepository.createUser(user);
    }

    public User getUser(String id) {
        return IUserRepository.findUserById(id);
    }
}
