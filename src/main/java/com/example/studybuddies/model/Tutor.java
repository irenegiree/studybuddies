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
    @Size(min = 1, message = "{Size.Tutor.firstName}")
    @Column(name = "first_name")
    private  String firstName;

    @NotEmpty(message = "The Last Name can not be empty")
    @Size(min = 1, message = "{Size.Tutor.lastName")
    @Column(name = "last_name")
    private  String lastName;

    @NotEmpty(message = "The Email can not be empty")
    @Email(message = "It should be a valid email")
    @Column(name = "email")
    private  String email;

    @NotEmpty(message = "Phone can not be empty")
    @Size(min = 10, max = 10, message = "Phone should have the size of 10")
    @Pattern(regexp = "^\\d{10}$", message = "Phone should be a 10")
    @Column(name = "phone")
    private  String  phone;

    @NotEmpty
    @Size(min = 6)
    @Column(name = "password")
    private String password;

    @NotEmpty
    @Size(min = 5)
    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @NotEmpty
    @Size(min = 5)
    @Column(name = "preferred_language")
    private String preferredLanguage;

    @NotEmpty
    @Size(min = 5)
    @Column(name = "hobbies")
    private  String hobbies;

    @Column(name = "subject1")
    private String subject1;

    @Column(name = "exp1")
    private String exp1;

    @Column(name = "pe1")
    private String pe1;


    @Column(name = "subject2")
    private String subject2;

    @Column(name = "exp2")
    private String exp2;

    @Column(name = "pe2")
    private String pe2;


    @Column(name = "subject3")
    private String subject3;

    @Column(name = "exp3")
    private String exp3;

    @Column(name = "pe3")
    private String pe3;

}
