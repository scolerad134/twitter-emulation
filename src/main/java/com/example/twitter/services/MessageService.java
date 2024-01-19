package com.example.twitter.services;

import com.example.twitter.models.Message;

import java.util.List;

public interface MessageService {
    public List<Message> findAll();
    public void save(Message message);
    public List<Message> findByTag(String tag);
}
