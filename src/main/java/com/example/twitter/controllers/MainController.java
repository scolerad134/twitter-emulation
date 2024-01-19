package com.example.twitter.controllers;


import com.example.twitter.models.Message;
import com.example.twitter.services.MessageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String main(Map<String, Object> model) {
        model.put("messages", messageService.findAll());
        return "main";
    }

    @PostMapping("/main")
    public String add(@RequestParam String text, @RequestParam String tag, Map<String, Object> model) {
        Message message = new Message();
        message.setText(text);
        message.setTag(tag);
        messageService.save(message);
        return "redirect:/main";
    }

    @GetMapping("/filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        List<Message> messages;

        if (filter == null || filter.isEmpty())
            model.put("messages", messageService.findAll());
        else
            model.put("messages", messageService.findByTag(filter));

        return "main";
    }
}
