package com.example.studybuddies.controller;

import com.example.studybuddies.model.*;
import com.example.studybuddies.repository.CurrentLoggedInUserRepository;
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
    @Autowired
    private CurrentLoggedInUserRepository cluRepo;

    @GetMapping("/message/{id}")
    public String messageForm(Model model, ModelMap mm,
                              @PathVariable(value ="id") long tutorId,
                              HttpSession session) {
        // create model attribute to bind form data

        Student student = studentService.getStudentByEmail(cluRepo.findAll().get(0).getEmail());
        Tutor tutor = tutorService.getTutorById(tutorId);

        Message message = new Message();
        message.setStudentID(student.getId());
        message.setCreatedAt(new Date());
        message.setTutorID(tutorId);
        if (student != null) {message.setSender(student.getFirstName()+" "+student.getLastName());}
        if (tutor != null) {message.setReceiver(tutor.getFirstName()+" "+tutor.getLastName());}
        
        model.addAttribute("message", message);
        return "message_form";
    }

    @GetMapping("/reply-message/{id}")
    public String replyMessageForm(Model model, ModelMap mm,
                                   @PathVariable(value ="id") long previousMessageID,
                              HttpSession session) {
        // create model attribute to bind form data
        Message previousMsg = messageService.getMessageById(previousMessageID);

        Student student = studentService.getStudentById(previousMsg.getStudentID());
        Tutor tutor = tutorService.getTutorById(previousMsg.getTutorID());

        Message message = new Message();
        message.setStudentID(student.getId());
        message.setCreatedAt(new Date());
        message.setTutorID(tutor.getId());
        message.setSender(previousMsg.getReceiver());
        message.setReceiver(previousMsg.getSender());

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

       // Student student = studentService.getStudentByEmail(cluRepo.findAll().get(0).getEmail());


//        Tutor tutor = (Tutor) session.getAttribute("tutor");

        Student student = null;
        Tutor tutor = null;
        if(cluRepo.findAll().size()>0) {
            CurrentLoggedInUser clu = cluRepo.findAll().get(0);
            if (clu.getRole().equals("ROLE_STUDENT")) {
                student = studentService.getStudentByEmail(cluRepo.findAll().get(0).getEmail());
            } else {
                tutor = tutorService.getTutorByEmail(cluRepo.findAll().get(0).getEmail());
            }
        }

        messages = messageService.getAllMessages();
        List<Message> filteredMessages = new ArrayList<>();

        if (student != null) {




            Student finalStudent = student;
            messages.forEach(msg -> {
                if (msg.getStudentID() == finalStudent.getId()) {
                    filteredMessages.add(msg);
                }
         });



        } else if(tutor != null){
            Tutor finalTutor = tutor;
            messages.forEach(msg -> {
                if (msg.getTutorID() == finalTutor.getId()) {
                    filteredMessages.add(msg);
                }
            });
        // If the student is not found in the session, you can handle it here.
        // For example, you can show a message or redirect to an error page.

        }
        else {
            model.addAttribute("error", "Unable to get messages for current user!");
        }
        model.addAttribute("messages",filteredMessages);
        return "message_list";

    }

    @GetMapping("/delete-message/{id}")
    public String deleteMessage (@PathVariable(value ="id") long id) {
        messageService.deleteMessage(id);
        return "redirect:/message_list";
    }


}
// SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");