package com.example.twitter.services;

import com.example.twitter.models.User;

public interface UserService {
    public User findByUsername(String username);

    public boolean createUser(User user);
}
