package com.example.twitter.repositories;

import com.example.twitter.models.Message;
import com.example.twitter.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {

    public Page<Message> findAll(Pageable pageable);

    public Page<Message> findByTag(String tag, Pageable pageable);

    Page<Message> findAllByAuthor(User user, Pageable pageable);
}
