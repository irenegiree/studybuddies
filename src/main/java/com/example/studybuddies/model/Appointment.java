package com.example.studybuddies.model;

import com.nimbusds.jose.shaded.gson.internal.bind.DateTypeAdapter;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.Date;

@Setter
@Getter
@ToString
@Entity
@Table(name = "appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Temporal(TemporalType.DATE) @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name = "apptDate")
    private Date apptDate;


   @DateTimeFormat(pattern="HH:mm")
    @Column(name = "apptTime")
    private LocalTime apptTime;

    @Column(name = "duration")
    private int duration;


    @Column(name = "link")
    private String link;


    @Column(name = "studentID")
    private long studentID;

    @Column(name = "tutorID")
    private long tutorID;

    @Column(name = "tutorName")
    private String tutorName;

    @Column(name = "subject")
    private String subject;

}

