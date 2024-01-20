package com.example.twitter.controllers;


import com.example.twitter.models.User;
import com.example.twitter.services.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final UserServiceImpl userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(User user, Model model) {
        if (!userService.createUser(user)) {
            model.addAttribute("errorMessage", "User with username: " + user.getUsername() + " already exists");
            return "registration";
        }
        return "redirect:/login";
    }
}






