package com.example.twitter.controllers;


import com.example.twitter.models.Message;
import com.example.twitter.models.User;
import com.example.twitter.services.MessageServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Map;


@Controller
@RequiredArgsConstructor
public class MainController {

    private final MessageServiceImpl messageService;

    @GetMapping("/")
    public String greeting() {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model, Principal principal) {
        if (filter == null || filter.isEmpty())
            model.addAttribute("messages", messageService.findAll());
        else
            model.addAttribute("messages", messageService.findByTag(filter));

        model.addAttribute("filter", filter);
        model.addAttribute("user", messageService.getUserByPrincipal(principal));

        return "main";
    }


    @PostMapping("/main")
    public String add(@AuthenticationPrincipal User user, @Valid Message message,
                      BindingResult result, Model model, MultipartFile file) throws IOException {

        if (result.hasErrors()) {
            model.addAttribute("isErrors", true);
        } else {
            messageService.save(user, message.getText(), message.getTag(), file);
        }

        return "redirect:/main";
    }
}
