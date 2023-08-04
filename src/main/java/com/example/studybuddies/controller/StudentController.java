package com.example.studybuddies.controller;

import com.example.studybuddies.model.Student;
import com.example.studybuddies.service.StudentService;
import com.example.studybuddies.service.TutorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/register-student")
    public String registerStudentForm(Model model) {
        // create model attribute to bind form data
        Student student = new Student();
        model.addAttribute("student", student);
        return "student_create_form";
    }

    @PostMapping("/create-student")
    public String createStudent(@Valid Student student, BindingResult result) throws Exception {
        if (result.hasErrors()) {
            return "student_create_form";
        }
        final Student stu = studentService.createStudent(student);

        return "redirect:/";
    }

    @PostMapping("/update-student")
    public String updateStudent(Student student) throws Exception {
        final Student stu = studentService.updateStudent(student);

        return "redirect:/";
    }

    @GetMapping("/edit-student/{id}")
    public String studentEditForm(@PathVariable(value = "id") long id, Model model) {

        // get employee from the service
        Student student = studentService.getStudentById(id);

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("student", student);
        return "student_update_form";
    }

    @GetMapping("/delete-student/{id}")
    public String deleteStudent(@PathVariable(value = "id") long id) {

        // get employee from the service
        studentService.deleteStudent(id);

        // set employee as a model attribute to pre-populate the form

        return "redirect:/students";
    }

    @GetMapping("/student-profile/{id}")
    public String studentProfile(@PathVariable(value = "id") long id, Model model) {

        // get employee from the service
        Student student = studentService.getStudentById(id);

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("student", student);
        return "student_profile";
    }

    @Autowired
    TutorService tutorService;
    @GetMapping("/student-findAtutor")
    public String studentFindATutor (Model model) {

        model.addAttribute("tutorList", tutorService.getAllTutors());

        return "student_findAtutor";
    }
}
