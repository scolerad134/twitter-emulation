package com.example.twitter.services;

import com.example.twitter.models.Message;
import com.example.twitter.models.User;
import com.example.twitter.repositories.MessageRepository;
import com.example.twitter.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    private final UserRepository userRepository;

    @Override
    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    @Override
    public void save(User user, String text, String tag) {
        Message message = new Message();
        message.setText(text);
        message.setTag(tag);
        message.setAuthor(user);
        log.info("Saving new Message. Text: {}; Tag: {}", message.getText(), message.getTag());
        messageRepository.save(message);
    }


//    @Override
//    public User getUserByPrincipal(User user) {
//        if (principal == null) return new User();
//        return userRepository.findByUsername(principal.getName());
//    }


    @Override
    public List<Message> findByTag(String tag) {
        return messageRepository.findByTag(tag);
    }
}







