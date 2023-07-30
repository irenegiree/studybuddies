package com.example.studybuddies.controller;

import com.example.studybuddies.model.Message;
import com.example.studybuddies.model.Student;
import com.example.studybuddies.model.Tutor;
import com.example.studybuddies.service.MessageService;
import com.example.studybuddies.service.StudentService;
import com.zaxxer.hikari.util.ConcurrentBag;
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


import java.util.*;

@SessionAttributes({"student", "tutor"})
@Controller
@AllArgsConstructor
public class MessagesController {

    @Autowired
    MessageService messageService;


    @GetMapping("/message")
    public String messageForm(Model model, ModelMap mm,
                              @RequestParam(name="keyword",defaultValue="")String keyword,
                              HttpSession session) {
        // create model attribute to bind form data

        Student student = (Student) session.getAttribute("student");
        Tutor tutor = (Tutor) session.getAttribute("tutor");

        Message message = new Message();
        message.setCreatedAt(new Date());
        if (student != null) {message.setSender(student.getFirstName()+" "+student.getLastName());}

        // Waiting the tutor data model to be ready
        //   if (tutor != null) {message.setReceiver(tutor.getFirstName()+" "+tutor.getLastName());}
        
        model.addAttribute("message", message);
        return "message_form";
    }
    @PostMapping("/create-message")
    public String createMessage(@Valid Message message, BindingResult result) throws Exception {
        if (result.hasErrors()) {
            message.setCreatedAt(new Date());
            return "message_form";
        }

        message.setCreatedAt(new Date());
                messageService.createMessage(message);


        return "redirect:/message_list";
    }

    @GetMapping(path="/message_list")
    public String messageList (Model model, ModelMap mm, @RequestParam(name="keyword", defaultValue = "")String keyword,
                                   HttpSession session){
        List<Message> messages;
        Student student = (Student) session.getAttribute("student");
        Tutor tutor = (Tutor) session.getAttribute("tutor");
        messages = messageService.getAllMessages();

        if (student != null)
        {
            messages.forEach(msg->{
                if (msg.getStudentID() != student.getId()){
                    messages.remove(msg);
                }
            });
        }

        model.addAttribute("messages", messages);

        return "message_list";

    }

}
// SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");