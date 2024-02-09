package com.example.twitter.services;

import com.example.twitter.models.Image;
import com.example.twitter.models.Message;
import com.example.twitter.models.User;
import com.example.twitter.repositories.ImageRepository;
import com.example.twitter.repositories.MessageRepository;
import com.example.twitter.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
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

    private final ImageRepository imageRepository;

    @Override
    public Message save(User currentUser, Message message, String text, String tag, MultipartFile file) throws IOException {
        Image image;

//        message = messageRepository.findById(message.getId()).orElse(null);


        if (message.getAuthor().equals(currentUser)) {
            if (!StringUtils.isEmpty(text)) message.setText(text);
            if (!StringUtils.isEmpty(tag)) message.setTag(tag);
        }

        if (!file.isEmpty()) {

            image = toImageEntity(file);
            message.addImageToMessage(image);

//            if (!message.getImageList().isEmpty()) {
//                image.setId(message.getImageList().get(0).getId());
//            }

//            imageRepository.save(image);
        }

        return messageRepository.save(message);
    }

    @Override
    public Page<Message> findAll(Pageable pageable) {
        return messageRepository.findAll(pageable);
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
    public Page<Message> findByTag(String tag, Pageable pageable) {
        return messageRepository.findByTag(tag, pageable);
    }
}







