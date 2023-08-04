package com.example.studybuddies.controller;

import com.example.studybuddies.model.Appointment;
import com.example.studybuddies.model.Message;
import com.example.studybuddies.model.Student;
import com.example.studybuddies.model.Tutor;
import com.example.studybuddies.service.MessageService;
import com.example.studybuddies.service.StudentService;
import com.example.studybuddies.service.TutorService;
import com.zaxxer.hikari.util.ConcurrentBag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;


import java.util.*;

@SessionAttributes({"student", "tutor"})
@Controller
@AllArgsConstructor
public class MessagesController {

    @Autowired
    MessageService messageService;
    @Autowired
    StudentService studentService;
    @Autowired
    TutorService tutorService;

    @GetMapping("/message/{id}")
    public String messageForm(Model model, ModelMap mm,
                              @PathVariable(value ="id") long tutorId,
                              HttpSession session) {
        // create model attribute to bind form data

        Student student = (Student) session.getAttribute("student");
       // student = studentService.getStudentById(2);
        Tutor tutor = tutorService.getTutorById(tutorId);

        Message message = new Message();
        message.setStudentID(student.getId());
        message.setCreatedAt(new Date());
        message.setTutorID(tutorId);
        if (student != null) {message.setSender(student.getFirstName()+" "+student.getLastName());}

        // Waiting the tutor data model to be ready
        if (tutor != null) {message.setReceiver(tutor.getFirstName()+" "+tutor.getLastName());}
        
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

        //Student student = studentService.getStudentById(1);

        Tutor tutor = (Tutor) session.getAttribute("tutor");
        messages = messageService.getAllMessages();


        if (student != null) {

            List<Message> filteredMessages = new ArrayList<>();

            messages.forEach(msg -> {
                if (msg.getStudentID() == student.getId()) {
                    filteredMessages.add(msg);

                }
            });


            model.addAttribute("messages",filteredMessages);
        } else {
        // If the student is not found in the session, you can handle it here.
        // For example, you can show a message or redirect to an error page.
        model.addAttribute("error", "Student not found in session");
        }
        return "message_list";

    }

    @GetMapping("/delete-message/{id}")
    public String deleteMessage (@PathVariable(value ="id") long id) {
        messageService.deleteMessage(id);
        return "redirect:/message_list";
    }


}
// SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");