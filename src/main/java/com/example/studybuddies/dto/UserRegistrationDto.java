package com.example.studybuddies.dto;

public class UserRegistrationDto {
    private int studentId;
    private int tutorId;
    private String email;
    private String password;
    private String role;

    public UserRegistrationDto(){

    }

    public UserRegistrationDto(int studentId, int tutorId, String email, String password) {
        super();
        this.studentId = studentId;
        this.tutorId = tutorId;
        this.email = email;
        this.password = password;
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
    public void setRole(String role) {
        this.role = role;
    }
}