package com.example.twitter.controllers;


import com.example.twitter.models.Message;
import com.example.twitter.models.User;
import com.example.twitter.services.MessageServiceImpl;
import com.example.twitter.services.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Controller
@RequiredArgsConstructor
public class MainController {

    private final MessageServiceImpl messageService;

    @GetMapping("/")
    public String greeting(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("name", user.getUsername());

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

    @GetMapping("/user-messages/{user}")
    public String userMessages(@AuthenticationPrincipal User currentUser,
                               @PathVariable User user, Model model,
                               @RequestParam(required = false) Message message) {

        Set<Message> messages = user.getMessages();

        model.addAttribute("userChannel", user);
        model.addAttribute("subscriptionsCount", user.getSubscriptions().size());
        model.addAttribute("subscribersCount", user.getSubscribers().size());
        model.addAttribute("messages", messages);
        model.addAttribute("message", message);
        model.addAttribute("isSubscriber", user.getSubscribers().contains(currentUser));
        model.addAttribute("isCurrentUser", currentUser.equals(user));

        return "userMessages";
    }

    @PostMapping("/user-messages/{user}")
    public String updateMessage(@AuthenticationPrincipal User currentUser, @PathVariable Long user,
                                @RequestParam(name = "id", required = false) Message message,
                                @RequestParam("text") String text, @RequestParam("tag") String tag,
                                MultipartFile file) throws IOException {


        if (message != null)
            messageService.save(currentUser, message, text, tag, file);

        return "redirect:/user-messages/" + user;
    }
}

