package com.example.studybuddies.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "school")
    private String school;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Column(name = "preferred_language")
    private String preferredLanguage;

    @Column(name = "hobbies")
    private String hobbies;

    @Column(name = "subject1")
    private String subject1;

    @Column(name = "exp1")
    private String exp1;

    @Column(name = "subject2")
    private String subject2;

    @Column(name = "exp2")
    private String exp2;

    @Column(name = "subject3")
    private String subject3;

    @Column(name = "exp3")
    private String exp3;

}
