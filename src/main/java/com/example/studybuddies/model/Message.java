package com.example.studybuddies.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long messageID;

    @Column(name = "createdAt")
    private Date createdAt;

    @Size(min = 1)
    @Column(name = "sender")
    private String sender;

    @Size(min = 1)
    @Column(name = "receiver")
    private String receiver;

    @Size(min = 1)
    @Column(name = "messageContent")
    private String messageContent;

    @Size(min = 1)
    @Column(name = "file_att")
    private String file_att;

    @Column(name = "studentID")
    private long studentID;

    @Column(name = "tutorID")
    private long tutorID;

}


