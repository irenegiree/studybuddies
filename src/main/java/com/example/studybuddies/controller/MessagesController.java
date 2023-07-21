package com.example.studybuddies.controller;

import com.example.studybuddies.model.Message;
import com.example.studybuddies.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class MessagesController {

    @Autowired
    MessageService messageService;

    @GetMapping("/message")
    public String messageForm(Model model) {
        // create model attribute to bind form data
        Message message = new Message();
        Date date = new Date();
        // SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        message.setCreatedAt(date);
        model.addAttribute("message", message);
        return "message_form";
    }
    @PostMapping("/create-message")
    public String createMessage(@Valid Message message, BindingResult result) throws Exception {
        if (result.hasErrors()) {
            return "message_form";
        }
        final Message mgs = messageService.createMessage(message);

        return "redirect:/";
    }
}
