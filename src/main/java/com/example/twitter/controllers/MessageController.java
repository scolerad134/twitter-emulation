package com.example.twitter.controllers;


import com.example.twitter.models.Message;
import com.example.twitter.models.User;
import com.example.twitter.services.MessageServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;


@Controller
@RequiredArgsConstructor
public class MessageController {

    private final MessageServiceImpl messageService;

    @GetMapping("/")
    public String greeting(@AuthenticationPrincipal User user, Model model) {
        if (user != null) model.addAttribute("user", user.getUsername());
        return "greeting";
    }

    @GetMapping("/main")
    public String main(
            @RequestParam(required = false, defaultValue = "") String filter,
            Model model,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
            Principal principal) {

        Page<Message> page;

        if (filter == null || filter.isEmpty())
            page = messageService.findAll(pageable);
        else
            page = messageService.findByTag(filter, pageable);

        model.addAttribute("page", page);
        model.addAttribute("url", "/main");

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
                               @RequestParam(required = false) Message message,
                               @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {

        Page<Message> page = messageService.findAllByAuthor(user, pageable);

        model.addAttribute("userChannel", user);
        model.addAttribute("subscriptionsCount", user.getSubscriptions().size());
        model.addAttribute("subscribersCount", user.getSubscribers().size());
        model.addAttribute("page", page);
        model.addAttribute("url", "/user-messages/" + user.getId());
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

    @GetMapping("/user-messages/delete/{id}")
    public String deleteMessage(
            @PathVariable(name = "id") Message message,
            HttpServletRequest request) {

        messageService.deleteMessage(message);

        String url = request.getHeader("referer").substring(8);
        url = url.substring(url.indexOf('/'));

        return "redirect:" + url;
    }
}







