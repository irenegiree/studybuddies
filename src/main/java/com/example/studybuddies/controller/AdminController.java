package com.example.studybuddies.controller;

import com.example.studybuddies.model.Student;
import com.example.studybuddies.model.Tutor;
import com.example.studybuddies.service.StudentService;
import com.example.studybuddies.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    StudentService studentService;

    @GetMapping("/students")
    public String showStudentList(Model model) {
        // create model attribute to bind form data
        model.addAttribute("studentList", studentService.getAllStudents());
        return "student_list";
    }

    @Autowired
    TutorService tutorService;

    @GetMapping("/tutors-approved")
    public String showApprovedTutorList(Model model){
        model.addAttribute("tutorList", tutorService.getAllApprovedTutors());

        return "tutor_list";
    }

    @GetMapping("/tutors-unapproved")
    public String showUnapprovedTutorList(Model model){
        model.addAttribute("tutorList", tutorService.getAllUnpprovedTutors());

        return "approve_tutor_list";
    }

    @GetMapping("/approve-tutor/{id}")
    public String tutorProfile(@PathVariable(value = "id") long id, Model model) {
        System.out.println("************************************************** approve-tutor: "+id);
        // get employee from the service
        Tutor tutor = tutorService.getTutorById(id);
        tutorService.approveTutor(tutor);

        // set employee as a model attribute to pre-populate the form
        return "redirect:/admin/tutors-unapproved";
    }

}
