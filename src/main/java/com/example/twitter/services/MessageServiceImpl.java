package com.example.twitter.services;

import com.example.twitter.models.Image;
import com.example.twitter.models.Message;
import com.example.twitter.models.User;
import com.example.twitter.repositories.MessageRepository;
import com.example.twitter.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public Message save(User user, String text, String tag, MultipartFile file) throws IOException {
        Message message = new Message();
        Image image;

        message.setText(text);
        message.setTag(tag);
        message.setAuthor(user);

        if (!file.isEmpty()) {
            image = toImageEntity(file);
            message.addImageToMessage(image);
        }

        log.info("Saving new Message. Text: {}; Tag: {}", message.getText(), message.getTag());
        return messageRepository.save(message);
    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }


    @Override
    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByUsername(principal.getName());
    }

    @Override
    public Message findById(Long id) {
        return messageRepository.findById(id).orElse(null);
    }

    @Override
    public List<Message> findByTag(String tag) {
        return messageRepository.findByTag(tag);
    }
}







