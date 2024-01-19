package com.example.twitter.repositories;

import com.example.twitter.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    public List<Message> findByTag(String tag);
}
