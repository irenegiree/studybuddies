package com.example.studybuddies.controller;

import com.example.studybuddies.model.Appointment;
import com.example.studybuddies.model.Message;
import com.example.studybuddies.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;


import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@AllArgsConstructor
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
        // final Message mgs =
        System.out.println(message.getMessageContent());
                messageService.createMessage(message);


        return "redirect:/message_list";
    }

    @GetMapping(path="/message_list")
    public String messageList (Model model, ModelMap mm, @RequestParam(name="keyword", defaultValue = "")String keyword,
                                   HttpSession session){
        List<Message> messages;
        messages = messageService.getAllMessages();
        System.out.println(messages.size());
        model.addAttribute("messages", messages);

        return "message_list";

    }

}
