package com.example.studybuddies.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name = "tutors")
public class Tutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "The First Name can not be empty")
    @Size(min = 1, message = "{Size.Tutor.FirstName}")
    @Column(name = "first_name")
    private  String FirstName;

    @NotEmpty(message = "The Last Name can not be empty")
    @Size(min = 1, message = "{Size.Tutor.LastName")
    @Column(name = "last_name")
    private  String LastName;

    @NotEmpty(message = "The Email can not be empty")
    @Email(message = "It should be a valid email")
    @Column(name = "email")
    private  String Email;

    @NotEmpty(message = "Phone can not be empty")
    @Size(min = 10, max = 10, message = "Phone should have the size of 10")
    @Pattern(regexp = "^\\d{10}$", message = "Phone should be a 10")
    @Column(name = "phone")
    private  String  Phone;

    @NotEmpty
    @Size(min = 6)
    @Column(name = "password")
    private String Password;

    @NotEmpty
    @Size(min = 5)
    @Column(name = "date_of_birth")
    private String DateOfBirth;

    @NotEmpty
    @Size(min = 5)
    @Column(name = "preferred_language")
    private String PreferredLanguage;

    @NotEmpty
    @Size(min = 5)
    @Column(name = "hobbies")
    private  String Hobbies;

    @Column(name = "subject")
    private String Subject;

    @Column(name = "exp")
    private String Exp;

    @Column(name = "pe")
    private String pe;


    @Column(name = "subject2")
    private String Subject2;

    @Column(name = "exp2")
    private String Exp2;

    @Column(name = "pe2")
    private String pe2;


    @Column(name = "subject3")
    private String Subject3;

    @Column(name = "exp3")
    private String Exp3;

    @Column(name = "pe3")
    private String pe3;


    @Column(name = "subject4")
    private String Subject4;

    @Column(name = "exp4")
    private String Exp4;

    @Column(name = "pe4")
    private String pe4;


    @Column(name = "subject5")
    private String Subject5;

    @Column(name = "exp5")
    private String Exp5;

    @Column(name = "pe5")
    private String pe5;







}
