package com.example.twitter.services;

import com.example.twitter.models.Message;
import com.example.twitter.repositories.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Override
    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    @Override
    public void save(Message message) {
        log.info("Saving new Message. Text: {}; Tag: {}", message.getText(), message.getTag());
        messageRepository.save(message);
    }

    @Override
    public List<Message> findByTag(String tag) {
        return messageRepository.findByTag(tag);
    }
}







