package com.example.twitter.services;

import com.example.twitter.models.Message;
import com.example.twitter.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

public interface MessageService {
    public Message save(User currentUser, Message message, String text, String tag, MultipartFile file) throws IOException;
    public Page<Message> findAll(Pageable pageable);
    public Message save(User user, String text, String tag, MultipartFile image) throws IOException;
    public Page<Message> findByTag(String tag, Pageable pageable);
    public User getUserByPrincipal(Principal principal);
    public Message findById(Long id);
    public Page<Message> findAllByAuthor(User user, Pageable pageable);

    public void deleteMessage(Message message);
}
