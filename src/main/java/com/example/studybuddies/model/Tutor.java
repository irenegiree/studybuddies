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
    @Size(min = 1, message = "{Size.Student.FirstName}")
    @Column(name = "First Name")
    private  String firstName;

    @NotEmpty(message = "The Last Name can not be empty")
    @Size(min = 1, message = "{Size.Student.LastName}")
    @Column(name = "Last Name")
    private  String LastName;

    @NotEmpty(message = "The Email can not be empty")
    @Email(message = "It should be a valid email")
    @Column(name = "Email")
    private  String email;

    @NotEmpty(message = "Phone can not be empty")
    @Size(min = 10, max = 10, message = "Phone should have the size of 10")
    @Pattern(regexp = "^\\d{10}$", message = "Phone should be a 10")
    @Column(name = "Phone")
    private  String  phone;

    @NotEmpty
    @Size(min = 6)
    @Column(name = "Password")
    private String password;

    @NotEmpty
    @Size(min = 5)
    @Column(name = "Date Of Birth")
    private String dateofbirth;

    @NotEmpty
    @Size(min = 5)
    @Column(name = "Preferred Language")
    private String preferredlanguage;

    @NotEmpty
    @Size(min = 5)
    @Column(name = "Hobbies")
    private  String hobbies;

    @Column(name = "Subject")
    private String subject;

    @Column(name = "Exp")
    private String exp;

    @Column(name = "PE")
    private String pe;


    @Column(name = "Subject2")
    private String subject2;

    @Column(name = "Exp2")
    private String exp2;

    @Column(name = "PE2")
    private String pe2;


    @Column(name = "Subject3")
    private String subject3;

    @Column(name = "Exp3")
    private String exp3;

    @Column(name = "PE3")
    private String pe3;


    @Column(name = "Subject4")
    private String subject4;

    @Column(name = "Exp4")
    private String exp4;

    @Column(name = "PE4")
    private String pe4;


    @Column(name = "Subject5")
    private String subject5;

    @Column(name = "Exp5")
    private String exp5;

    @Column(name = "PE5")
    private String pe5;







}
