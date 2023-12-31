package com.example.studybuddies.controller;

import com.example.studybuddies.jwt.impl.UserDetailsImpl;
import com.example.studybuddies.model.CurrentLoggedInUser;
import com.example.studybuddies.model.Student;
import com.example.studybuddies.repository.CurrentLoggedInUserRepository;
import com.example.studybuddies.service.AppointmentService;
import com.example.studybuddies.service.StudentService;
import com.example.studybuddies.service.TutorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@SessionAttributes({"student", "tutor"})
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    private CurrentLoggedInUserRepository cluRepo;

    @GetMapping("/registration/student")
    public String registerStudentForm(Model model) {
        // create model attribute to bind form data
        Student student = new Student();
        model.addAttribute("student", student);
        return "student_create_form";
    }

    @PostMapping("/registration/student")
    public String createStudent(@Valid Student student, BindingResult result) throws Exception {
        if (result.hasErrors()) {
            return "student_create_form";
        }
        final Student stu = studentService.createStudent(student);

        return "redirect:/login";
    }


    @PostMapping("/update-student")
    public String updateStudent(Student student) throws Exception {
        System.out.println("*********************************************** update "+student.getId());
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


    @GetMapping("/edit-student")
    public String currentStudentEditForm( Model model) {

        // get employee from the service
        Student student = studentService.getStudentByEmail(cluRepo.findAll().get(0).getEmail());

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

//    @PreAuthorize("hasAnyRole('ROLE_STUDENT')")
    @GetMapping("/student-profile")
    public String studentProfile(Model model) {
        // get employee from the service
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        List<CurrentLoggedInUser> cluList = cluRepo.findAll();
        Student student = studentService.getStudentByEmail(cluList.get(0).getEmail());

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("student", student);
        return "student_profile";
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
        model.addAttribute("tutorList", tutorService.getMatchingTutors());
        return "student_findAtutor";
    }

    @GetMapping("/student-alltutors")
    public String studentAllTutors (Model model) {
        model.addAttribute("tutorList", tutorService.getAllApprovedTutors());
        return "student_findAtutor";
    }
}
