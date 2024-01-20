package com.example.twitter.services;

import com.example.twitter.models.User;

import java.util.List;

public interface UserService {
    public User findByUsername(String username);

    public boolean createUser(User user);

    public List<User> findAll();

    public User save(User user);
}
