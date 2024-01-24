package com.example.twitter.services;


import com.example.twitter.models.Image;
import com.example.twitter.repositories.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    public Image findById(Long id) {
        return imageRepository.findById(id).orElse(null);
    }
}
