package com.example.studybuddies.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Time;
import java.util.Date;

@Setter
@Getter
@ToString
@Entity
@Table(name = "appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long appointmentID;

    @Column(name = "apptDate")
    private Date apptDate;


    @Column(name = "apptTime")
    private Time apptTime;

    @Column(name = "duration")
    private int duration;


    @Size(min = 1)
    @Column(name = "link")
    private String link;


    @Column(name = "studentID")
    private long studentID;

    @Column(name = "tutorID")
    private long tutorID;

    @Size(min = 1)
    @Column(name = "subject")
    private String subject;

}

