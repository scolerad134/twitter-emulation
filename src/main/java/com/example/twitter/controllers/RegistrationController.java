package com.example.twitter.controllers;


import com.example.twitter.models.User;
import com.example.twitter.models.dto.CaptchaResponseDto;
import com.example.twitter.services.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
public class RegistrationController {

//    private final static String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";

    private final UserServiceImpl userService;

    private final RestTemplate restTemplate;

    @Value("${recaptcha.secret}")
    private String secret;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@RequestParam("g-recaptcha-response") String captchaResponse,
                               @Valid User user, BindingResult result, Model model) {

        String url = "https://www.google.com/recaptcha/api/siteverify";
        String params = "?secret=6LeuEWYpAAAAACfxWvpagWXgltZHt1H6ze_9E3Gx&response=" + captchaResponse;
        String completeUrl = url+params;

        CaptchaResponseDto captchaResponseDto =
                restTemplate.postForObject(completeUrl, null, CaptchaResponseDto.class);

        if (captchaResponseDto.isSuccess()) {

            if (result.hasErrors()) {
                model.addAttribute("validError", true);
            } else {

                log.info("SUCCESS");

                model.addAttribute("user", user);

                if (!userService.createUser(user)) {
                    model.addAttribute("errorMessage", "User with username: " + user.getUsername() + " already exists");
                    return "registration";
                } else {
                    log.info("Create new user");
                }

                return "redirect:/login";
            }
        } else {
            log.info("FAIL");
            model.addAttribute("captchaError", "Fill captcha");
        }


        return "registration";
    }
}






