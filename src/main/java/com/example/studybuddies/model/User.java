package com.example.studybuddies.model;

import jakarta.persistence.*;

import java.util.Collection;


@Entity
@Table(name =  "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_id")
    private Integer studentId;

    @Column(name = "tutor_id")
    private Integer tutorId;

    private String email;

    private String password;

    private String role;

    public User() {

    }

    public User(int studentId, int tutorId, String email, String password, String role) {
        super();
        this.studentId = studentId;
        this.tutorId = tutorId;
        this.email = email;
        this.password = password;
        this.role = role;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getStudentId() {
        return studentId;
    }
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    public int getTutorId() {
        return tutorId;
    }
    public void setTutorId(int tutorId) {
        this.tutorId = tutorId;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRole() {
        return role;
    }
    public void setRoles(String role) {
        this.role = role;
    }

}
