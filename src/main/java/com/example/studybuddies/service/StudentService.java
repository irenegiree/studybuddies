package com.example.studybuddies.service;

import com.example.studybuddies.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    List<Student> getAllStudents();

    Student createStudent(Student student);
}
