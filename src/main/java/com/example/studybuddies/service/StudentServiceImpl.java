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
        Student student1 = new Student();
        student1.setId(student.getId());
        student1.setEmail(student.getEmail());
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        student1.setPhone(student.getPhone());
        student1.setPassword(student.getPassword());
        student1.setSubject1(student.getSubject1());
        student1.setSubject2(student.getSubject2());
        student1.setSubject3(student.getSubject3());
        student1.setDateOfBirth(student.getDateOfBirth());
        student1.setExp1(student.getExp1());
        student1.setExp2(student.getExp2());
        student1.setExp3(student.getExp3());
        student1.setSchool(student.getSchool());
        student1.setPreferredLanguage(student.getPreferredLanguage());
        return this.studentRepository.save(student1);
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
