package com.example.twitter.services;

import com.example.twitter.models.Message;
import com.example.twitter.models.User;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

public interface MessageService {
    public List<Message> findAll();
    public Message save(User user, String text, String tag, MultipartFile image) throws IOException;
    public List<Message> findByTag(String tag);
    public User getUserByPrincipal(Principal principal);
    public Message findById(Long id);
}
