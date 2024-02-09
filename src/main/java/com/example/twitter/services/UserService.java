package com.example.twitter.services;

import com.example.twitter.models.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    public User findById(Long id);
    public User findByUsername(String username);

    public boolean createUser(User user);

    public List<User> findAll();

    public User save(User user, String username, Map<String, String> form);

    void updateProfile(User user, String username, String password);

    void subscribe(User currentUser, User user);

    void unsubscribe(User currentUser, User user);
}
