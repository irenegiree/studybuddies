package com.example.studybuddies.controller;

import com.example.studybuddies.model.Student;
import com.example.studybuddies.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
    @Autowired
    StudentService studentService;
    @GetMapping("/students")
    public String showStudentList(Model model) {
        // create model attribute to bind form data
        model.addAttribute("studentList", studentService.getAllStudents());
        return "student_list";
    }

}
