package com.example.studybuddies.service;

import com.example.studybuddies.model.Student;
import com.example.studybuddies.model.User;
import com.example.studybuddies.repository.StudentRepository;
import com.example.studybuddies.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student createStudent(Student student) {

        Student savedStudent = this.studentRepository.save(student);
        User user = new User((int)savedStudent.getId(), 0, student.getEmail(), encoder.encode(student.getPassword()), "ROLE_STUDENT");
        userRepository.save(user);
        return savedStudent;
    }

    @Override
    public Student updateStudent(Student student) {
        return this.studentRepository.save(student);
    }

    @Override
    public void deleteStudent(long id) {
        this.studentRepository.deleteById(id);
    }

    @Override
    public Student getStudentById(long id) {
        Optional< Student > optional = studentRepository.findById(id);
        Student student = null;
        if (optional.isPresent()) {
            student = optional.get();
        } else {
            throw new RuntimeException(" Student not found for id :: " + id);
        }
        return student;
    }

    @Override
    public Student getStudentByEmail(String email) {
        Optional<Student> optional = studentRepository.findByEmail(email);
        Student student = null;
        if (optional.isPresent()) {
            student = optional.get();
        } else {
            throw new RuntimeException(" Student not found for id :: " + email);
        }
        return student;
    }
}
