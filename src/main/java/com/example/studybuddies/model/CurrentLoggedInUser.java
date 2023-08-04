package com.example.studybuddies.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "currentLoggedInUser")
public class CurrentLoggedInUser {
    @Id
    @Column(name = "email")
    private String email;

    @Column(name="role")
    private String role;
}
