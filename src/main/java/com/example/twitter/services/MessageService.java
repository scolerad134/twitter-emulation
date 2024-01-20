package com.example.twitter.services;

import com.example.twitter.models.Message;
import com.example.twitter.models.User;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

public interface MessageService {
    public List<Message> findAll();
    public void save(User user, String text, String tag);
    public List<Message> findByTag(String tag);
//    public User getUserByPrincipal(User principal);
}
