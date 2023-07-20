package com.example.studybuddies.controller;

import com.example.studybuddies.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MessagesController {

    @Autowired
    MessageService messageService;


}
