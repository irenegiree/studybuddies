package com.example.studybuddies.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.NumberFormat;

@Setter
@Getter
@ToString
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "The First Name can't be empty")
    @Size(min = 1, message = "{Size.Student.FirstName}")
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty(message = "The First Name can't be empty")
    @Size(min = 1, message = "{Size.Student.LastName}")
    @Column(name = "last_name")
    private String lastName;

    @NotEmpty(message = "Email can't be empty")
    @Email(message = "It should be a valid email")
    @Column(name = "email")
    private String email;

    @NotEmpty(message = "Phone can't be empty")
    @Size(min = 10, max = 10, message = "Phone should have the size of 10")
    @Pattern(regexp = "^\\d{10}$", message = "Phone should be a 10 digit number")
    @Column(name = "phone")
    private String phone;

    @NotEmpty
    @Size(min = 6)
    @Column(name = "password")
    private String password;

    @NotEmpty
    @Size(min = 5)
    @Column(name = "school")
    private String school;

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
