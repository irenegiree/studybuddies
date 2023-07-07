package com.example.studybuddies.controller;

import com.example.studybuddies.model.Student;
import com.example.studybuddies.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping()
    public Student createStudent(Student student) throws Exception {
        final Student stu = studentService.createStudent(student);

        return stu;
    }
}
